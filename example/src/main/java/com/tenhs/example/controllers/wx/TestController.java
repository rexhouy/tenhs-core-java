package com.tenhs.example.controllers.wx;

import com.tenhs.core.auth.SessionContext;
import com.tenhs.core.auth.admin.AdminUser;
import com.tenhs.core.auth.wechat.WxUser;
import com.tenhs.core.history.HistoryService;
import com.tenhs.sys.mappers.DepartmentMapper;
import com.tenhs.example.controllers.WxController;
import com.tenhs.example.services.CacheExampleService;
import com.tenhs.example.mappers.TestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wx")
@Slf4j
@RequiredArgsConstructor
public class TestController extends WxController {

    final CacheExampleService cacheService; // Service 缓存示例
    final TestMapper testRepo;
    final DepartmentMapper deptRepo;
    final HistoryService historyService;

    @GetMapping("/test")
    String index(HttpSession session) {
        AdminUser u1 = SessionContext.currentAdmin();
        WxUser u2 = SessionContext.currentWxUser();
        cacheService.get("test");
        return "project/dashboard/index";
    }

}
