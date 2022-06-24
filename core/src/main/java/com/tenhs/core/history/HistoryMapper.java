package com.tenhs.core.history;

import com.tenhs.core.web.ApplicationMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface HistoryMapper extends ApplicationMapper {

    @Select("select * from histories where id = #{id}")
    History findById(@Param("id") long id);
    
    @Select("select * from histories where archiveable_id = #{id}, archiveable_type = #{type} order by create_at asc")
    List<History> findHistory(@Param("id") Long id, @Param("type") String type);
}
