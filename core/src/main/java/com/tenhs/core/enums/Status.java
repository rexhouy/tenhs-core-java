package com.tenhs.core.enums;

public enum Status {
    
    enabled(1),
    
    disabled(0);
    
    private int code;
    Status(int code) {
        this.code = code;
    }
}
