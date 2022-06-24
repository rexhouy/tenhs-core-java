package com.tenhs.example.controllers.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tenhs.core.auth.SessionContext;
import com.tenhs.core.auth.annotations.AuthAdmin;
import com.tenhs.core.history.HistoryService;
import com.tenhs.core.web.ApplicationController;
import com.tenhs.sys.mappers.DepartmentMapper;
import com.tenhs.example.controllers.AdminController;
import com.tenhs.example.services.CacheExampleService;
import com.tenhs.example.mappers.TestMapper;
import com.tenhs.example.models.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class DashboardController extends AdminController {

    final CacheExampleService cacheService; // Service 缓存示例
    final TestMapper testRepo;
    final DepartmentMapper deptRepo;
    final HistoryService historyService;

    @GetMapping()
    @AuthAdmin(roles = {"admin"})
    String index(HttpSession session) {
        cacheService.put("test", "1");
        cacheService.get("test");
        session.setAttribute("dashboard", "1");
        return "project/dashboard/index";
    }

    @GetMapping("/test")
    @AuthAdmin(roles = {"super"})
    String createTest() {
        // 插入测试ApplicationMapperProvider
//        Department d = new Department();
//        d.setType("Department");
//        d.setName("test group");
//        deptRepo.insert(d);
        
        // Enum测试
//        Test t = new Test();
//        t.setStatus(Status.enabled);
//        testRepo.insert(t);
//        Test t1 = testRepo.findById(t.getId());
        SessionContext.currentAdmin();
        // 分页测试
        Page<Test> tests = PageHelper.startPage(1, 1).doSelectPage(() -> testRepo.findAll());
        long count = tests.getTotal();
        return "project/dashboard/index";
    }

    @GetMapping("/test_history")
    String testHistory() {
        List<Test> tests = historyService.list(5l, Test.class);
        tests.forEach(h -> log.info(h.toString()));
        return "project/dashboard/index";
    }
    
}
