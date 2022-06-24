package com.tenhs.core.auth.wechat;

import cn.hutool.core.bean.BeanUtil;
import com.tenhs.core.ApplicationConstant;
import com.tenhs.core.wechat.WechatService;
import com.tenhs.core.wechat.dto.WxOpenid;
import com.tenhs.core.wechat.dto.WxUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WxUserService {
    
    final WxUserMapper wxUserMapper;
    final WechatService wechatService;

    /**
     * 从微信获取用户信息 
     */
    public WxUser getUserInfo(String code, boolean persist) {
        WxUser user = getBaseInfo(code);
        if (!user.isPersisted() && "snsapi_userinfo".equals(ApplicationConstant.WX_AUTH_TYPE)) {
            WxUserInfo userInfo = wechatService.getUserInfo(user.getOpenid(), user.getAccessToken());
            BeanUtil.copyProperties(userInfo, user);
        }
        if (persist && !user.isPersisted()) {
            wxUserMapper.insert(user);
        }
        return user;
    }

    /**
     * snsapi_base
     */
    private WxUser getBaseInfo(String code) {
        WxOpenid openid = wechatService.getOpenid(code);
        WxUser user = wxUserMapper.findByOpenid(openid.getOpenid());
        if (user == null) {
            user = new WxUser().setOpenid(openid.getOpenid());
        }
        user.setAccessToken(openid.getAccessToken());
        return user;
    }
}
