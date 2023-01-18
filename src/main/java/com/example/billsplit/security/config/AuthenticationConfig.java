package com.example.billsplit.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthenticationConfig {
    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }
}
