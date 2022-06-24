package com.tenhs.core.auth.admin;

import com.tenhs.core.ApplicationConstant;
import com.tenhs.core.web.ApplicationController;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminUserController extends ApplicationController {
    
    final AdminUserService adminLoginService;
    
    final String DEFAULT_REDIRECT = "/admin";

    @GetMapping("/sign_in")
    String signInPage(@RequestParam(value = "redirect", required = false) String redirect, Model model) {
        if (StringUtils.isBlank(redirect)) {
            redirect = DEFAULT_REDIRECT;
        }
        model.addAttribute("redirect", redirect);
        return "framework/sign_in";
    }
    
    @PostMapping("/sign_in")
    String signIn(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest req) {
        AdminUser user = adminLoginService.auth(username, password);
        if (user == null) {
            model.addAttribute("error", "用户名或密码错误");
            return "framework/sign_in";
        } else {
            req.getSession().setAttribute(ApplicationConstant.ADMIN_SESSION_KEY, user);
        }
        return "redirect:" + DEFAULT_REDIRECT;
    }
    
    @PostMapping("/sign_out")
    String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:" + DEFAULT_REDIRECT;
    }
}
