package com.haykz.GymCRM.service;

import com.haykz.GymCRM.model.Trainee;
import com.haykz.GymCRM.model.Trainer;
import com.haykz.GymCRM.model.User;
import com.haykz.GymCRM.repository.TraineeRepository;
import com.haykz.GymCRM.repository.TrainerRepository;
import com.haykz.GymCRM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public User createProfile(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(generateUsername(firstName, lastName));
        user.setPassword(generateRandomPassword(12));
        user.setActive(true);
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
        List<Trainer> trainers = trainerRepository.getAllTrainersByUsername(username);
        List<Trainee> trainees = traineeRepository.getAllTraineesByUsername(username);
        return trainees.stream().anyMatch(trainee -> trainee.getUser().getUsername().equals(username)) ||
                trainers.stream().anyMatch(trainer -> trainer.getUser().getUsername().equals(username));
    }
}
