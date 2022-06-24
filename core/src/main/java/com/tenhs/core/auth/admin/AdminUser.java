package com.tenhs.core.auth.admin;

import com.tenhs.core.web.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 这里定义了admins表所需要的最低限度的字段
 */
@Data
@Accessors(chain = true)
@Table(name = "admins")
public class AdminUser implements Serializable {
    private Long id;

    private String mobile;

    private String role;

    private String encryptedPassword;

    private Date createdAt;

    private Date updateAt;

}
