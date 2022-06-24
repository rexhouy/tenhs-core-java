package com.tenhs.core;

public class ApplicationConstant {

    /**
     * Session中用于保存admin user的key
     */
    public static final String ADMIN_SESSION_KEY = "admin";

    /**
     * Session中用于保存wechat user的key
     */
    public static final String WX_SESSION_KEY = "wx";

    /**
     * 403URL
     */
    public static final String UNAUTHORIZED_PATH = "/403.html";

    /**
     * 微信登录类型["snsapi_base", "snsapi_userinfo"]
     */
    public static final String WX_AUTH_TYPE = "snsapi_base";
    
}
