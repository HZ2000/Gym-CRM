package com.haykz.GymCRM.service;

import com.haykz.GymCRM.dao.TraineeDAO;
import com.haykz.GymCRM.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {
    private TraineeDAO traineeDAO;

    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    public Trainee createTrainee(Trainee trainee) {
        return traineeDAO.saveTrainee(trainee);
    }

    public Trainee updateTrainee(Trainee trainee) {
        return traineeDAO.updateTrainee(trainee);
    }

    public void deleteTrainee(Long traineeId) {
        traineeDAO.deleteTraineeById(traineeId);
    }

    public Trainee getTraineeById(Long traineeId) {
        return traineeDAO.findTraineeById(traineeId);
    }

    public List<Trainee> getAllTrainees() {
        return traineeDAO.findAllTrainees();
    }
}
