package com.example.mlpnotificationservice.configuration;

import com.example.mlpnotificationservice.service.email.EmailTemplateFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public EmailTemplateFactory emailTemplateFactory() {
        return new EmailTemplateFactory();
    }
}
