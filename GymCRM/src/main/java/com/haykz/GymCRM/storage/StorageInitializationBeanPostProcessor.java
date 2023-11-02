package com.haykz.GymCRM.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haykz.GymCRM.model.Trainee;
import com.haykz.GymCRM.model.Trainer;
import com.haykz.GymCRM.model.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class StorageInitializationBeanPostProcessor implements BeanPostProcessor {
    private final InMemoryStorage storage;

    @Value("${data.file.path}")
    private String jsonFilePath;

    @Autowired
    public StorageInitializationBeanPostProcessor(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(jsonFilePath);
            HashMap<String, Object> data = objectMapper.readValue(file, new TypeReference<>() {});
            List<Trainer> trainers = objectMapper.convertValue(data.get("trainers"), new TypeReference<>() {});
            List<Trainee> trainees = objectMapper.convertValue(data.get("trainees"), new TypeReference<>() {});
            List<Training> trainings = objectMapper.convertValue(data.get("trainings"), new TypeReference<>() {});
            storage.getStorage().put("trainers", trainers);
            log.info("Trainers loaded: {}", trainers);
            storage.getStorage().put("trainees", trainees);
            log.info("Trainees loaded: {}", trainees);
            storage.getStorage().put("trainings", trainings);
            log.info("Trainings loaded: {}", trainings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
