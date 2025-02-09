package com.rajumajhi.ecom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig {





    @Configuration
    public class WebConfigs implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://192.168.1.203:5173")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        }
    }

}
