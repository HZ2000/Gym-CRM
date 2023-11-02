package com.haykz.GymCRM.config;

import com.haykz.GymCRM.storage.InMemoryStorage;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public InMemoryStorage storage() {
        return new InMemoryStorage();
    }
}
