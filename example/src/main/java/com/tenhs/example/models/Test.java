package com.tenhs.example.models;

import com.tenhs.core.enums.Status;
import com.tenhs.core.web.Table;
import lombok.Data;

@Data
@Table(name = "test")
public class Test {

    private Long id;

    private String user;

    private String departmentId;
    
    private Status status;
}
