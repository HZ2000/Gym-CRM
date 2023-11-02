package com.haykz.GymCRM.controller;

import com.haykz.GymCRM.model.*;
import com.haykz.GymCRM.service.TraineeService;
import com.haykz.GymCRM.service.TrainerService;
import com.haykz.GymCRM.service.TrainingService;
import com.haykz.GymCRM.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class GymCRMFacade {
    private final TrainerService trainerService;
    private final TraineeService traineeService;
    private final TrainingService trainingService;
    private final UserService userService;

    @Autowired
    public GymCRMFacade(TrainerService trainerService, TraineeService traineeService,
                        TrainingService trainingService, UserService userService) {
        this.trainerService = trainerService;
        this.traineeService = traineeService;
        this.trainingService = trainingService;
        this.userService = userService;
    }

    public Trainer createTrainer(String firstName, String lastName) {
        User user = userService.createProfile(firstName, lastName);
        Trainer trainer = new Trainer();
        trainer.setSpecialization(new TrainingType());
        trainer.setUser(user);
        return trainerService.createTrainer(trainer);
    }

    public Trainee createTrainee(String firstName, String lastName){
        User user = userService.createProfile(firstName, lastName);
        Trainee trainee = new Trainee();
        trainee.setAddress("Random address");
        trainee.setDateOfBirth(new Date());
        trainee.setUser(user);
        return traineeService.createTrainee(trainee);
    }

    public Training createTraining(){
        Training training = new Training();
        training.setTrainee(traineeService.getTraineeById(1L));
        training.setTrainer(trainerService.getTrainerById(1L));
        training.setTrainingType(new TrainingType());
        training.setTrainingDuration(new BigDecimal(2));
        return trainingService.createTraining(training);
    }
}
