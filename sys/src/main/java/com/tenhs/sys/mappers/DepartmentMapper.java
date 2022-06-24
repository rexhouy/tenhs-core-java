package com.tenhs.sys.mappers;

import com.tenhs.core.web.ApplicationMapper;
import com.tenhs.sys.models.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentMapper extends ApplicationMapper {
    @Select("select * from `groups` where type='department' and id = #{id}")
    Department findById(@Param("id") long id);

    @Select("select * from `groups` where type='department' and ancestry = #{ancestry}")
    List<Department> findByAncestry(@Param("ancestry") String ancestry);
    
    default Department parent(Department d) {
        if ("1".equals(d.getAncestryDepth())) {
            return null;
        }
        List<String> ids = List.of(d.getAncestry().split(","));
        String id = ids.get(ids.size() - 1);
        return findById(Long.valueOf(id));
    }

    default Department root(Department d) {
        if ("1".equals(d.getAncestryDepth())) {
            return null;
        }
        return findById(Long.valueOf(List.of(d.getAncestry().split(",")).get(0)));
    }

    default List<Department> children(Department d) {
        return findByAncestry(d.getAncestryDepth() == 0 ? d.getId().toString() : d.getAncestry() + "," + d.getId());
    }

    default List<Department> siblings(Department d) {
        return findByAncestry(d.getAncestry());
    }
}
