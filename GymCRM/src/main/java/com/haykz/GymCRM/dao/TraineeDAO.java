package com.haykz.GymCRM.dao;

import com.haykz.GymCRM.model.Trainee;

import java.util.List;

public interface TraineeDAO {
    Trainee saveTrainee(Trainee trainee);
    Trainee updateTrainee(Trainee trainee);
    Trainee findTraineeById(Long id);
    List<Trainee> findAllTrainees();
    void deleteTraineeById(Long id);
}
