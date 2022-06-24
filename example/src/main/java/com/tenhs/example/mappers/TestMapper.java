package com.tenhs.example.mappers;

import com.tenhs.core.web.ApplicationMapper;
import com.tenhs.example.models.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper extends ApplicationMapper {

    @Select("select * from test where id = #{id}")
    Test findById(@Param("id") long id);
    
    @Select("select * from test")
    List<Test> findAll();

}
