package com.tenhs.example.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "example")
public class CacheExampleService {

    @Cacheable(key = "#key")
    public String get(String key) {
        return ".....";
    }

    @CachePut(key = "#key")
    public String put(String key, String value) {
        return value;
    }

    @CacheEvict(key = "#key")
    public void delete(String key) {
        
    }
}
