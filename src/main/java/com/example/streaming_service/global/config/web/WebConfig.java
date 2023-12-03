package com.example.streaming_service.global.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost", "*")
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","))
                .allowedHeaders("Authorization", "Content-Type", "Access-Control-Allow-Origin")
                .exposedHeaders("Custom-Header")
                .maxAge(3600);
    }
}
