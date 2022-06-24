package com.tenhs.core.config;

import com.tenhs.core.auth.admin.AdminAuthInterceptor;
import com.tenhs.core.auth.wechat.WxAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    final AdminAuthInterceptor adminAuthInterceptor;
    
    final WxAuthInterceptor wxAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 后台用户登录配置
        registry.addInterceptor(adminAuthInterceptor);
        // 微信用户登录配置
        registry.addInterceptor(wxAuthInterceptor);
    }
}
