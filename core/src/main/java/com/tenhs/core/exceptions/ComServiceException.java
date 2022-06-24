package com.tenhs.core.exceptions;

import org.thymeleaf.util.StringUtils;

public class ComServiceException extends RuntimeException {

    private String redirect;

    public ComServiceException(String message, String redirect) {
        super(message);
        this.redirect = redirect;
    }

    public ComServiceException(String message, Throwable cause, String redirect) {
        super(message, cause);
        this.redirect = redirect;
    }

    public String getRedirect() {
        if (StringUtils.isEmptyOrWhitespace(this.redirect)) {
            return "/404.html";
        } else {
            return this.redirect;
        }
    }
}
