package com.tenhs.core.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.thymeleaf.spring5.expression.Fields;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 注意：Provider只会查找返回String的方法，所以返回值不能随意改
 */
public class ApplicationMapperProvider<T> {

    public String update(T obj) {
        Map<String, String> fields;
        try {
            fields = getFields(obj, false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return new SQL() {
            {
                UPDATE(getTableName(obj));
                fields.forEach((k, v) -> VALUES(k, "#{" + v + "}"));
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String insert(T obj) {
        Map<String, String> fields;
        try {
            fields = getFields(obj, false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return new SQL() {
            {
                INSERT_INTO(getTableName(obj));
                fields.forEach((k, v) -> VALUES(k, "#{" + v + "}"));
            }
        }.toString();
    }

    public String delete(T obj) {
        return new SQL() {
            {
                DELETE_FROM(getTableName(obj));
                WHERE("id = #{id}");
            }
        }.toString();
    }
    
    private Map<String, String> getFields(Object obj, boolean includeNull) throws IllegalAccessException {
        Map<String, String> ret = new HashMap<>();
        for (Field f : obj.getClass().getDeclaredFields()) {
            String name = f.getName();
            if (!"id".equals(name) && accessible(f, obj) && (includeNull || f.get(obj) != null)) {
                ret.put(camel2underscore(name), name);
            }
        }
        return ret;
    }

    /**
     * 获取对应的table名称 
     */
    private String getTableName(Object obj) {
        Table t = obj.getClass().getAnnotation(Table.class);
        if (t != null && StringUtils.isNotBlank(t.name())) {
            return "`" + t.name() + "`";
        }
        return camel2underscore(obj.getClass().getSimpleName());
    }

    /**
     * Field是否可访问 
     */
    private boolean accessible(Field f, Object obj) {
        f.setAccessible(true);
        try {
            obj.getClass().getMethod("get" + StringUtils.capitalize(f.getName()));
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

    /**
     * 驼峰转下划线 
     */
    private String camel2underscore(String s) {
        return "`" + 
                List.of(s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"))
                .stream().map(String::toLowerCase)
                .collect(Collectors.joining("_")) +
                "`";
    }
    
}
