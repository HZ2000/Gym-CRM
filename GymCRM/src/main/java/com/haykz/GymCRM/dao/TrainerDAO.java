package com.haykz.GymCRM.dao;

import com.haykz.GymCRM.model.Trainer;

import java.util.List;

public interface TrainerDAO {
    Trainer saveTrainer(Trainer trainer);
    Trainer updateTrainer(Trainer trainer);
    Trainer findTrainerById(Long id);
    List<Trainer> findAllTrainers();
    void deleteTrainerById(Long id);
}
