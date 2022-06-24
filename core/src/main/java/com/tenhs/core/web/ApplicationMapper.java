package com.tenhs.core.web;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

public interface ApplicationMapper {

    @UpdateProvider(method = "update", type = ApplicationMapperProvider.class)
    int update(Object obj);
    
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @InsertProvider(method = "insert", type = ApplicationMapperProvider.class)
    int insert(Object obj);
    
    @DeleteProvider(method = "delete", type = ApplicationMapperProvider.class)
    int delete(Object obj);
}
