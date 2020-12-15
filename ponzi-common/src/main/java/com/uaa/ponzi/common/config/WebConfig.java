package com.uaa.ponzi.common.config;

import com.uaa.ponzi.common.converter.EnumConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private EnumConverterFactory enumConverterFactory;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("POST")
                .maxAge(3600)
                .allowedOrigins("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 注入参数转换枚举
        registry.addConverterFactory(enumConverterFactory);
    }

}
