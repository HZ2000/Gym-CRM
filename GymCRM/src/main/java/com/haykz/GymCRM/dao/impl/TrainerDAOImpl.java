package com.haykz.GymCRM.dao.impl;

import com.haykz.GymCRM.dao.TrainerDAO;
import com.haykz.GymCRM.model.Trainer;
import com.haykz.GymCRM.storage.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TrainerDAOImpl implements TrainerDAO {
    private InMemoryStorage storage;
    private Long idCounter = 1L;

    @Autowired
    public void setInMemoryStorage (InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        trainer.setId(idCounter);
        List<Trainer> trainers = (List<Trainer>) storage.getStorage().get("trainers");
        trainers.add(trainer);
        storage.getStorage().put("trainers", trainers);
        idCounter++;
        log.info("Trainer saved: {}", trainer);
        return trainer;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        List<Trainer> trainers = (List<Trainer>) storage.getStorage().get("trainers");
        trainers.stream().findFirst().filter(trainer1 -> trainer1.getId().equals(trainer.getId())).ifPresent(trainer1 -> {
            trainers.remove(trainer1);
            trainers.add(trainer);
        });
        storage.getStorage().put("trainers", trainers);
        log.info("Trainer updated: {}", trainer);
        return trainer;
    }

    @Override
    public Trainer findTrainerById(Long id) {
        List<Trainer> trainers = (List<Trainer>) storage.getStorage().get("trainers");
        Trainer trainer = trainers.stream().filter(trainer1 -> trainer1.getId().equals(id)).findFirst().orElse(null);
        log.info("Trainer found: {}", trainer);
        return trainer;
    }

    @Override
    public List<Trainer> findAllTrainers() {
        List<Trainer> trainers = (List<Trainer>) storage.getStorage().get("trainers");
        log.info("All trainers found: {}", trainers);
        return trainers;
    }

    @Override
    public void deleteTrainerById(Long id) {
        List<Trainer> trainers = (List<Trainer>) storage.getStorage().get("trainers");
        Trainer trainer = trainers.stream().findFirst().filter(trainer1 -> trainer1.getId().equals(id)).orElse(null);
        trainers.remove(trainer);
        storage.getStorage().put("trainers", trainers);
        log.info("Trainer deleted: {}", trainer);
    }
}
