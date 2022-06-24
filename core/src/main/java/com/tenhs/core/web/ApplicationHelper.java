package com.tenhs.core.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 定义的方法可以给页面使用，页面上通过@h.method()使用
 */
@Configuration
public class ApplicationHelper {

    @Bean("h")
    public ApplicationHelper appHelper() {
        return new ApplicationHelper();
    }

    public String displayDate(Date d) {
        if (d == null) {
            return "";
        }
        return DateFormatUtils.format(d, "yyyy-MM-dd");
    }

    public String displayDateTime(Date d) {
        if (d == null) {
            return "";
        }
        return DateFormatUtils.format(d, "yyyy-MM-dd HH:mm:ss");
    }

    public List<Integer> pagenateList(int number, int totalPages) {
        Integer start = number - 5;
        Integer end = number + 5;
        if (start < 0) {
            end = end - start;
            start = 0;
        }
        if (end > totalPages) {
            start = start - end + totalPages;
            start = start < 0 ? 0 : start;
            end = totalPages;
        }
        return IntStream.range(start, end).boxed().collect(Collectors.toList());
    }

    protected <T> T updateAttr(Map params, T model, List<String> requiredParams) {
        CopyOptions opts = CopyOptions.create();
        opts.setPropertiesFilter((f, o) -> {
            return requiredParams.contains(f.getName());
        });
        BeanUtil.fillBeanWithMap(params, model, true, opts);
        return model;
    }

}
