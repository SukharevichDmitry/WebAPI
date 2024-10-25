package org.example.config;

import feign.RequestInterceptor;
import org.example.controller.TokenHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    private static final Logger logger = LoggerFactory.getLogger(FeignClientConfig.class);

    @Autowired
    private TokenHolder tokenHolder;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            if (!template.url().startsWith("/auth/")) {
                String token = tokenHolder.getToken();
                logger.info("Using token: {}", token);
                if (tokenHolder.isTokenValid()) {
                    template.header("Authorization", "Bearer " + token);
                } else {
                    logger.warn("Invalid JWT token: {}", token);
                    throw new IllegalArgumentException("Invalid JWT token.");
                }
            } else {
                logger.info("No token added for request to: {}", template.url());
            }
        };
    }
}
