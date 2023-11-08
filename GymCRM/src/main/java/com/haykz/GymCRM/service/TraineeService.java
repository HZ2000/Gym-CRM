package com.haykz.GymCRM.service;

import com.haykz.GymCRM.dto.trainee.CreateTraineeDto;
import com.haykz.GymCRM.dto.trainee.UpdateTraineeDto;
import com.haykz.GymCRM.model.Trainee;

import java.util.List;

public interface TraineeService {

    Trainee createTrainee(CreateTraineeDto traineeDto);

    Trainee updateTrainee(UpdateTraineeDto traineeDto);

    void deleteTrainee(Long traineeId);

    Trainee getTraineeById(Long traineeId);
    List<Trainee> getAllTrainees();
}
