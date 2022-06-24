package com.tenhs.core.auth.admin;

import com.tenhs.core.ApplicationConstant;
import com.tenhs.core.auth.annotations.AuthAdmin;
import com.tenhs.core.auth.annotations.SkipAuth;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class AdminAuthInterceptor implements HandlerInterceptor {

    ApplicationContext ctx;
    
    private String LOGIN_PATH = "/admin/sign_in";
    private String UNAUTHORIZED_PATH = "/403.html";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod h = (HandlerMethod) handler;
        AuthAdmin auth = getAuth(h);
        if (auth != null) {
            AdminUser admin = (AdminUser) request.getSession().getAttribute(ApplicationConstant.ADMIN_SESSION_KEY);
            if (admin == null) {
                // 未登录
                response.sendRedirect(LOGIN_PATH);
                return false;
            }
            if (!isAuthorized(admin, auth)) {
                // 无权限
                response.sendRedirect(UNAUTHORIZED_PATH);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 获取Annotation
     */
    private AuthAdmin getAuth(HandlerMethod h) {
        if (h.hasMethodAnnotation(SkipAuth.class)) {
            return null;
        }
        AuthAdmin auth = h.getMethodAnnotation(AuthAdmin.class);
        if (auth == null) {
            auth = h.getBean().getClass().getAnnotation(AuthAdmin.class);
        }
        return auth;
    }

    /**
     * 检查是否有权限 
     */
    private boolean isAuthorized(AdminUser admin, AuthAdmin auth) {
        List roles = List.of(auth.roles());
        if (roles.contains("*") || roles.contains(admin.getRole())) {
            return true;
        }
        return false;
    }

}
