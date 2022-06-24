package com.tenhs.core.web;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Slf4j
public class ApplicationController {

    @Value("${spring.profiles.active}")
    String env;
    
    protected boolean isProduction() {
        return "prod".equals(env);
    }

    protected boolean isDevelopment() {
        return "dev".equals(env);
    }

    /**
     * 下载Excel文件(使用easy excel)
     */
    protected void downloadExcel(HttpServletResponse response, String fileName, List data, Class klass) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), klass)
                    .sheet("").doWrite(data);
        } catch (Exception e) {
            log.error("下载文件名编码错误!");
        }
    }

    protected int currentPage(Map params) {
        Object p = params.get("page");
        if (p == null) {
            return 0;
        }
        return Integer.valueOf(p.toString());
    }

    protected String notFound() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    protected Object renderJson(Model model, HttpServletResponse resp) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        try {
            jsonConverter.write(model, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(resp));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
