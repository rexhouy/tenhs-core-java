package com.tenhs.core.wechat;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 微信AccessToken，需要使用定时任务刷新
 */
@Repository
@Mapper
public interface WxTokenMapper {
    
    @Select("select token from wx_tokens limit 1")
    public String get();

    @Select("select updated_at from wx_tokens limit 1")
    public Date getUpdatedAt();
    
    @Update("update wx_tokens set token = #{token}, created_at = now()")
    public void update(@Param("token") String token);
}