package com.tenhs.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    /**
     * 配置restTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().messageConverters(
                new MappingJackson2HttpMessageConverter(
                        new ObjectMapper().setPropertyNamingStrategy(
                                new PropertyNamingStrategy.SnakeCaseStrategy()
                        )
                )
        ).build();
    }
    
}
