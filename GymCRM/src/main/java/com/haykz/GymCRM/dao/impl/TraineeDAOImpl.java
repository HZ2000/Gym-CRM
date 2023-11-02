package com.haykz.GymCRM.dao.impl;

import com.haykz.GymCRM.dao.TraineeDAO;
import com.haykz.GymCRM.model.Trainee;
import com.haykz.GymCRM.storage.InMemoryStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TraineeDAOImpl implements TraineeDAO {
    private InMemoryStorage storage;
    private Long idCounter = 1L;

    @Autowired
    public void setInMemoryStorage (InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public Trainee saveTrainee(Trainee trainee) {
        trainee.setId(idCounter);
        List<Trainee> trainees = (List<Trainee>) storage.getStorage().get("trainees");
        trainees.add(trainee);
        storage.getStorage().put("trainees", trainees);
        idCounter++;
        log.info("Trainee saved: {}", trainee);
        return trainee;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        List<Trainee> trainees = (List<Trainee>) storage.getStorage().get("trainees");
        trainees.stream().findFirst().filter(trainee1 -> trainee1.getId().equals(trainee.getId())).ifPresent(trainee1 -> {
            trainees.remove(trainee1);
            trainees.add(trainee);
        });
        storage.getStorage().put("trainees", trainees);
        log.info("Trainee updated: {}", trainee);
        return trainee;
    }

    @Override
    public Trainee findTraineeById(Long id) {
        List<Trainee> trainees = (List<Trainee>) storage.getStorage().get("trainees");
        Trainee trainee = trainees.stream().filter(trainee1 -> trainee1.getId().equals(id)).findFirst().orElse(null);
        log.info("Trainee found: {}", trainee);
        return trainee;
    }

    @Override
    public List<Trainee> findAllTrainees() {
        List<Trainee> trainees = (List<Trainee>) storage.getStorage().get("trainees");
        log.info("All trainees found: {}", trainees);
        return trainees;
    }

    @Override
    public void deleteTraineeById(Long id) {
        List<Trainee> trainees = (List<Trainee>) storage.getStorage().get("trainees");
        Trainee trainee = trainees.stream().findFirst().filter(trainee1 -> trainee1.getId().equals(id)).orElse(null);
        trainees.remove(trainee);
        storage.getStorage().put("trainees", trainees);
        log.info("Trainee deleted: {}", trainee);
    }
}
