package com.haykz.GymCRM.service;

import com.haykz.GymCRM.dao.TrainerDAO;
import com.haykz.GymCRM.model.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TrainerServiceTest {

    @InjectMocks
    private TrainerService trainerService;

    @Mock
    private TrainerDAO trainerDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTrainerTest() {
        Trainer trainer = new Trainer();
        when(trainerDAO.saveTrainer(trainer)).thenReturn(trainer);

        Trainer createdTrainer = trainerService.createTrainer(trainer);

        assertEquals(trainer, createdTrainer);
    }

    @Test
    public void updateTrainerTest() {
        Trainer trainer = new Trainer();
        when(trainerDAO.updateTrainer(trainer)).thenReturn(trainer);

        Trainer updatedTrainer = trainerService.updateTrainer(trainer);

        assertEquals(trainer, updatedTrainer);
    }

    @Test
    public void getTrainerByIdTest() {
        Long trainerId = 1L;
        Trainer trainer = new Trainer();
        when(trainerDAO.findTrainerById(trainerId)).thenReturn(trainer);

        Trainer foundTrainer = trainerService.getTrainerById(trainerId);

        assertEquals(trainer, foundTrainer);
    }

    @Test
    public void getAllTrainersTest() {
        List<Trainer> trainers = new ArrayList<>();
        when(trainerDAO.findAllTrainers()).thenReturn(trainers);

        List<Trainer> allTrainers = trainerService.getAllTrainers();

        assertEquals(trainers, allTrainers);
    }
}