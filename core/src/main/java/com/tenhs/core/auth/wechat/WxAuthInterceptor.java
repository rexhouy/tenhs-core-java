package com.tenhs.core.auth.wechat;

import com.tenhs.core.ApplicationConstant;
import com.tenhs.core.auth.admin.AdminUser;
import com.tenhs.core.auth.annotations.AuthWx;
import com.tenhs.core.auth.annotations.AuthWx;
import com.tenhs.core.auth.annotations.SkipAuth;
import com.tenhs.core.wechat.WechatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WxAuthInterceptor implements HandlerInterceptor {

    final WechatService wxService;
    final WxUserMapper wxUserMapper;

    @Value("${spring.profiles.active}")
    String env; 
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod h = (HandlerMethod) handler;
        AuthWx auth = getAuth(h);
        if (auth != null) {
            WxUser user = (WxUser) request.getSession().getAttribute(ApplicationConstant.WX_SESSION_KEY);
            if (user == null) {
                // 未登录
                if ("dev".equals(env)) {
                    signInDev(request);
                    return true;
                }
                response.sendRedirect(wxService.authUrl(ApplicationConstant.WX_AUTH_TYPE, request.getRequestURI()));
                return false;
            }
            if (!isAuthorized(user, auth)) {
                // 无权限
                response.sendRedirect(ApplicationConstant.UNAUTHORIZED_PATH);
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
    private AuthWx getAuth(HandlerMethod h) {
        if (h.hasMethodAnnotation(SkipAuth.class)) {
            return null;
        }
        AuthWx auth = h.getMethodAnnotation(AuthWx.class);
        if (auth == null) {
            auth = h.getBean().getClass().getAnnotation(AuthWx.class);
        }
        return auth;
    }

    /**
     * 检查是否有权限 
     */
    private boolean isAuthorized(WxUser admin, AuthWx auth) {
        List roles = List.of(auth.roles());
        if (roles.contains("*") || roles.contains(admin.getRole())) {
            return true;
        }
        return false;
    }

    /**
     * 测试环境登录
     */
    private void signInDev(HttpServletRequest request) {
        WxUser user = wxUserMapper.findByOpenid("123");
        request.getSession().setAttribute(ApplicationConstant.WX_SESSION_KEY, user);
    }

}
