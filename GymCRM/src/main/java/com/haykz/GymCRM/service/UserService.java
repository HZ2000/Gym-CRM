package com.haykz.GymCRM.service;

import com.haykz.GymCRM.model.Trainee;
import com.haykz.GymCRM.model.Trainer;
import com.haykz.GymCRM.model.User;
import com.haykz.GymCRM.storage.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class UserService {
    private InMemoryStorage storage;

    @Autowired
    public void setInMemoryStorage(InMemoryStorage storage) {
        this.storage = storage;
    }

    public User createProfile(String firstName, String lastName) {
        final String username = generateUsername(firstName, lastName);
        String password = generateRandomPassword(12);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        log.info("User created: {}", user);
        return user;
    }

    private String generateUsername(String firstName, String lastName) {
        String username = firstName + "." + lastName;
        if (checkForExistingUsers(username)) {
            username = firstName + "." + lastName + "." + generateRandomPassword(5);
        }
        log.info("Username generated: {}", username);
        return username;
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        log.info("Password generated: {}", password);
        return password.toString();
    }

    private boolean checkForExistingUsers(String username) {
        List<Trainer> trainers = (List<Trainer>) storage.getStorage().get("trainers");
        List<Trainee> trainees = (List<Trainee>) storage.getStorage().get("trainees");
        return trainees.stream().anyMatch(trainee -> trainee.getUser().getUsername().equals(username)) ||
                trainers.stream().anyMatch(trainer -> trainer.getUser().getUsername().equals(username));
    }
}
