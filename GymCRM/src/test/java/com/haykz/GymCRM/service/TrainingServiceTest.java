package com.haykz.GymCRM.service;

import com.haykz.GymCRM.dao.TrainingDAO;
import com.haykz.GymCRM.model.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TrainingServiceTest {

    @InjectMocks
    private TrainingService trainingService;

    @Mock
    private TrainingDAO trainingDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTrainingTest() {
        Training training = new Training();
        when(trainingDAO.saveTraining(training)).thenReturn(training);

        Training createdTraining = trainingService.createTraining(training);

        assertEquals(training, createdTraining);
    }

    @Test
    public void getTrainingByIdTest() {
        Long trainingId = 1L;
        Training training = new Training();
        when(trainingDAO.findTrainingById(trainingId)).thenReturn(training);

        Training foundTraining = trainingService.getTrainingById(trainingId);

        assertEquals(training, foundTraining);
    }

    @Test
    public void getAllTrainingsTest() {
        List<Training> trainings = new ArrayList<>();
        when(trainingDAO.findAllTrainings()).thenReturn(trainings);

        List<Training> allTrainings = trainingService.getAllTrainings();

        assertEquals(trainings, allTrainings);
    }
}
