package com.tenhs.core.auth;

import com.tenhs.core.ApplicationConstant;
import com.tenhs.core.auth.admin.AdminUser;
import com.tenhs.core.auth.wechat.WxUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 使用ThreadLocal来存储用户信息
 */
public class SessionContext {
    
    public static AdminUser currentAdmin() {
        return (AdminUser) getSession().getAttribute(ApplicationConstant.ADMIN_SESSION_KEY);
    }

    public static WxUser currentWxUser() {
        return (WxUser) getSession().getAttribute(ApplicationConstant.WX_SESSION_KEY);
    }
    
    private static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest()
                .getSession();
    }
}


