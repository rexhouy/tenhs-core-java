package com.tenhs.core.auth.wechat;

import com.tenhs.core.ApplicationConstant;
import com.tenhs.core.web.ApplicationController;
import com.tenhs.core.wechat.WechatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wx")
@RequiredArgsConstructor
public class WxUserController extends ApplicationController {
    
    final WxUserService wxUserService;
    final WechatService wechatService;
    
    private static final String DEFAULT_REDIRECT = "/";
    
    /**
     * 用户授权后跳转到此URL
     * 此处将会获取用户信息并放入session中。
     */
    @GetMapping("/sign_in")
    public String signin(@PathVariable(value = "code") String code, @PathVariable(value = "state") String state, HttpServletRequest request) {
        WxUser user = wxUserService.getUserInfo(code, true);
        request.getSession().setAttribute(ApplicationConstant.WX_SESSION_KEY, user);
        return "redirect:" + wechatService.decodeState(state);
    }

    @PostMapping("/sign_out")
    String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:" + DEFAULT_REDIRECT;
    }

}
