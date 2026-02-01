package com.indianpharma.catalog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import com.indianpharma.catalog.Configuration;

@org.springframework.context.annotation.Configuration
public class RedisConfig {

    @Value("${redis.key}")
    private String redisPrimaryAccessKey;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return null;
    }
}
