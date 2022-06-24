package com.tenhs.core.auth.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminUserMapper {

    @Select("select * from admins where id = #{id}")
    AdminUser findById(@Param("id") long id);

    @Select("select * from admins where mobile = #{mobile}")
    AdminUser findByMobile(@Param("mobile") String mobile);
    
}
