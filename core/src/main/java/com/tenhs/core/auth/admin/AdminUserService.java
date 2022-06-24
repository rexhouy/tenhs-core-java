package com.tenhs.core.auth.admin;

import cn.hutool.crypto.digest.BCrypt;
import com.alibaba.excel.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    final AdminUserMapper userMapper;
    
    @Value("${app.secret}")
    private String secret;

    /**
     * 用户验证
     */
    public AdminUser auth(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return null;
        }
        AdminUser user = userMapper.findByMobile(username);
        if (user == null) {
            return null;
        }
        if (BCrypt.checkpw(password, user.getEncryptedPassword())) {
            return user;
        }
        return null;
    }

    /**
     * 用户注册
     */
    public AdminUser register(String username, String password, String passwordConfirmation) {
        // TODO 
        return null;
    }

}
