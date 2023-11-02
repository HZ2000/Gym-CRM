package com.haykz.GymCRM.dao.impl;

import com.haykz.GymCRM.dao.TrainingDAO;
import com.haykz.GymCRM.model.Training;
import com.haykz.GymCRM.storage.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TrainingDAOImpl implements TrainingDAO {

    private InMemoryStorage storage;
    private Long idCounter = 1L;

    @Autowired
    public void setInMemoryStorage (InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public Training saveTraining(Training training) {
        training.setId(idCounter);
        List<Training> trainings = (List<Training>) storage.getStorage().get("trainings");
        trainings.add(training);
        storage.getStorage().put("trainings", trainings);
        idCounter++;
        log.info("Training saved: {}", training);
        return training;
    }

    @Override
    public Training updateTraining(Training training) {
        List<Training> trainings = (List<Training>) storage.getStorage().get("trainings");
        trainings.stream().findFirst().filter(training1 -> training1.getId().equals(training.getId())).ifPresent(training1 -> {
            trainings.remove(training1);
            trainings.add(training);
        });
        storage.getStorage().put("trainings", trainings);
        log.info("Training updated: {}", training);
        return training;
    }

    @Override
    public Training findTrainingById(Long id) {
        List<Training> trainings = (List<Training>) storage.getStorage().get("trainings");
        Training training = trainings.stream().filter(training1 -> training1.getId().equals(id)).findFirst().orElse(null);
        log.info("Training found: {}", training);
        return training;
    }

    @Override
    public List<Training> findAllTrainings() {
        List<Training> trainings = (List<Training>) storage.getStorage().get("trainings");
        log.info("All trainings found: {}", trainings);
        return trainings;
    }

    @Override
    public void deleteTrainingById(Long id) {
        List<Training> trainings = (List<Training>) storage.getStorage().get("trainings");
        Training training = trainings.stream().findFirst().filter(training1 -> training1.getId().equals(id)).orElse(null);
        trainings.remove(training);
        storage.getStorage().put("trainings", trainings);
        log.info("Training deleted: {}", training);
    }
}
