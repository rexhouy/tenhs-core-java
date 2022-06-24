package com.tenhs.core.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
public class RestResult<T> implements Serializable {
    
    private String status;
    private T data;
    private String msg;
    
    public static <T> RestResult success(T obj) {
        return new RestResult("ok", obj, "");
    }
    
    public static <T> RestResult success(Object obj, String msg) {
        return new RestResult("ok", obj, msg);
    }

    public static <T> RestResult error(String msg) {
        return new RestResult("bad", null, msg);
    }
}
