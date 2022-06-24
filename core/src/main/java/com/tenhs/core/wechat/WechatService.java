package com.tenhs.core.wechat;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.tenhs.core.web.ApplicationService;
import com.tenhs.core.wechat.dto.WxOpenid;
import com.tenhs.core.wechat.dto.WxRet;
import com.tenhs.core.wechat.dto.WxUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WechatService extends ApplicationService {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Value("${wx.mchid}")
    private String mchid;
    @Value("${wx.sub_appid}")
    private String subAppid;
    @Value("${wx.sub_mchid}")
    private String subMchid;
    @Value("${wx.api_secret}")
    private String apiSecret;
    @Value("${app.host}")
    private String host;

    final RestTemplate restTemplate;
    
    static final String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
    static final String TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    static final String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 1. 跳转到用户授权页面
     *
     * @param scope    类型["snsapi_base", "snsapi_userinfo"]
     * @return 重定向页面
     */
    public String authUrl(String scope, String redirect) {
        String state = Base64.encode(host + "/wx/sign_in");
        Map params = Map.of(
                "appid", appid,
                "redirect_uri", host + redirect,
                "response_type", "code",
                "scope", scope,
                "state", state
        );
        return AUTH_URL + "?" + toQueryStr(params) + "#wechat_redirect";
    }

    /**
     * Decode 第1步中的state参数 
     */
    public String decodeState(String state) {
        return new String(Base64.decode(state));
    }

    /**
     * 2. 用户授权通过，使用code换取access_token和openid
     */
    public WxOpenid getOpenid(String code) {
        return restTemplate.getForObject(
                TOKEN_URL + "?" + toQueryStr(Map.of("appid", appid, "secret", secret, "code", code, "grant_type", "authorization_code")),
                WxOpenid.class
        );
    }

    /**
     * 3. 当scope=snsapi_userinfo时，获取用户信息
     */
    public WxUserInfo getUserInfo(String openid, String accessToken) {
        return restTemplate.getForObject(USERINFO_URL, WxUserInfo.class,
                Map.of("openid", openid, "access_token", accessToken, "lang", "zh_CN"));
    }

    /**
     * 发送模版消息
     */
    public boolean sendMsg(String openid, String templateId, String url, Map data) {
        Map params = Map.of(
                "appid", appid,
                "openid", openid,
                "template_id", templateId,
                "url", url,
                "data", JSONUtil.toJsonStr(data)
        );
        WxRet ret = restTemplate.postForObject(url, new HttpEntity<String>(JSONUtil.toJsonStr(params)), WxRet.class);
        return ret.succeed();
    }

    private String toQueryStr(Map params) {
        List<String> ret = new ArrayList<String>(params.keySet().size());
        for (Iterator itor = params.keySet().iterator(); itor.hasNext(); ) {
            String key = itor.next().toString();
            ret.add(key + "=" + params.get(key));
        }
        return ret.stream().collect(Collectors.joining("&"));
    }

}
