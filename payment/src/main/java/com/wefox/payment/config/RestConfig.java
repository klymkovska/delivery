package com.wefox.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate paymentValidationRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate errorLogRestTemplate() {
        return new RestTemplate();
    }
}
