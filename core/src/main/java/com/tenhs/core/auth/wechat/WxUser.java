package com.tenhs.core.auth.wechat;

import com.tenhs.core.web.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 这里定义了users表所需要的最低限度的字段
 */
@Data
@Accessors(chain = true)
@Table(name = "users")
public class WxUser implements Serializable {
    private Long id;

    private String openid;

    private String nickname;

    private String sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String privilege;

    private String unionid;
    
    private String role;
    
    private String accessToken;

    /**
     * 是否已存储
     */
    public boolean isPersisted() {
        return id != null;
    }
}
