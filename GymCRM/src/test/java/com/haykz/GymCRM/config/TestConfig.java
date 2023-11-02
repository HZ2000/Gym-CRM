package com.haykz.GymCRM.config;

import com.haykz.GymCRM.storage.InMemoryStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public InMemoryStorage storage() {
        return new InMemoryStorage();
    }
}
