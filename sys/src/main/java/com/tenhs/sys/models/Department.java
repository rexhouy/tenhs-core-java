package com.tenhs.sys.models;

import com.tenhs.core.web.Table;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "groups")
public class Department {

    private Long id;

    private String name;

    private String ancestry;

    private Integer ancestryDepth;

    private String type;

    private String status;

    private String memo;

    private Date createdAt;

    private Date updateAt;

}
