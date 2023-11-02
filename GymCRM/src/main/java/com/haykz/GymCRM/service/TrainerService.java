package com.haykz.GymCRM.service;

import com.haykz.GymCRM.dao.TrainerDAO;
import com.haykz.GymCRM.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private TrainerDAO trainerDAO;

    @Autowired
    public void setTrainerDAO(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerDAO.saveTrainer(trainer);
    }

    public Trainer updateTrainer(Trainer trainer) {
        return trainerDAO.updateTrainer(trainer);
    }

    public Trainer getTrainerById(Long trainerId) {
        return trainerDAO.findTrainerById(trainerId);
    }

    public List<Trainer> getAllTrainers() {
        return trainerDAO.findAllTrainers();
    }

}
