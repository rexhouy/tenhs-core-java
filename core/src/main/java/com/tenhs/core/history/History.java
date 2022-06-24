package com.tenhs.core.history;

import com.tenhs.core.web.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.HashMap;

@Data
@Accessors(chain = true)
@Table(name = "histories")
public class History {
    private Long id;

    private Long userId;

    private String memo;

    private Long archiveableId;

    private String archiveableType;

    private HashMap archive;

    private Date createdAt;

    private Date updateAt;
}
