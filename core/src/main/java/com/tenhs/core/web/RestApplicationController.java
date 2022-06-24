package com.tenhs.core.web;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * REST接口通用Controller
 */
@Slf4j
public class RestApplicationController extends ApplicationController {

    protected RestResult success(Object data) {
        return RestResult.success(data);
    }
    
    protected RestResult error(String msg) {
        return RestResult.error(msg);
    }
    
}
