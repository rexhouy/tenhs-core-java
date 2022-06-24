package com.tenhs.core.auth.wechat;

import com.tenhs.core.auth.admin.AdminUser;
import com.tenhs.core.web.ApplicationMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WxUserMapper extends ApplicationMapper {

    @Select("select * from users where openid = #{openid}")
    WxUser findByOpenid(@Param("openid") String openid);

}
