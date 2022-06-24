package com.tenhs.example.controllers.api;

import com.tenhs.core.exceptions.RestServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiTestController {
    
    @GetMapping("/test")
    Map test() {
        throw new RestServiceException("...");
    }

    @GetMapping("/test1")
    Map test1() {
        throw new RuntimeException("...");
    }
}
