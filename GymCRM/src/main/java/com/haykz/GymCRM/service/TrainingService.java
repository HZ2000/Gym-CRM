package com.haykz.GymCRM.service;

import com.haykz.GymCRM.dao.TrainingDAO;
import com.haykz.GymCRM.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    private TrainingDAO trainingDAO;

    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    public Training createTraining(Training training) {
        return trainingDAO.saveTraining(training);
    }

    public Training getTrainingById(Long trainingId) {
        return trainingDAO.findTrainingById(trainingId);
    }

    public List<Training> getAllTrainings() {
        return trainingDAO.findAllTrainings();
    }
}
