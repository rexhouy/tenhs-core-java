package com.tenhs.example.configs;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 需要启动xxljob服务端，然后修改配置文件
 */
@Configuration
public class XxlJobConfig {

    @Value("${xxljob.admin_address}")
    private String adminAddress;

    @Value("${xxljob.access_token}")
    private String accessToken;

    @Value("${xxljob.appname}")
    private String appname;

    @Value("${xxljob.ip}")
    private String ip;

    @Value("${xxljob.port}")
    private int port;

    @Value("${xxljob.logpath}")
    private String logPath;

    @Value("${xxljob.logretentiondays}")
    private int logRetentionDays;


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddress);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

}
