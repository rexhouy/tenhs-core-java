package com.tenhs.core.wechat.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class WxOpenid extends WxRet implements Serializable {

    private String accessToken;

    private int expiresIn;

    private String refreshToken;
    
    private String openid;
    
    private String scope;

}
