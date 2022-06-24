package com.tenhs.core.wechat.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class WxRet implements Serializable {

    private String errcode;

    private String errmsg;

    private String msgid;

    public boolean succeed() {
        return "0".equals(errcode);
    }

}
