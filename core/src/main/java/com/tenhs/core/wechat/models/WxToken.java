package com.tenhs.core.wechat.models;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class WxToken {
    private String token;
    private Date updatedAt;
}
