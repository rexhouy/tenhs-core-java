package com.tenhs.core.exceptions;

import com.tenhs.core.web.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

/**
 * REST通用服务端异常处理
 */
@RestControllerAdvice
@Slf4j
public class RestServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RestServiceException.class})
    public RestResult handleServiceException(RestServiceException e) throws Exception {
        log.error(e.getMessage(), e);
        return RestResult.error(e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public RestResult handleException(Exception e) throws Exception {
        log.error(e.getMessage(), e);
        return RestResult.error("系统异常");
    }
    
}
