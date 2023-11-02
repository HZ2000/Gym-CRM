package com.haykz.GymCRM.service;

import com.haykz.GymCRM.dao.TraineeDAO;
import com.haykz.GymCRM.model.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TraineeServiceTest {

    @InjectMocks
    private TraineeService traineeService;

    @Mock
    private TraineeDAO traineeDAO;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTraineeTest() {
        Trainee trainee = new Trainee();
        when(traineeDAO.saveTrainee(trainee)).thenReturn(trainee);

        Trainee createdTrainee = traineeService.createTrainee(trainee);

        assertEquals(trainee, createdTrainee);
    }

    @Test
    public void updateTraineeTest() {
        Trainee trainee = new Trainee();
        when(traineeDAO.updateTrainee(trainee)).thenReturn(trainee);

        Trainee updatedTrainee = traineeService.updateTrainee(trainee);

        assertEquals(trainee, updatedTrainee);
    }

    @Test
    public void deleteTraineeTest() {
        Long traineeId = 1L;
        traineeService.deleteTrainee(traineeId);

        verify(traineeDAO, times(1)).deleteTraineeById(traineeId);
    }

    @Test
    public void getTraineeByIdTest() {
        Long traineeId = 1L;
        Trainee trainee = new Trainee();
        when(traineeDAO.findTraineeById(traineeId)).thenReturn(trainee);

        Trainee foundTrainee = traineeService.getTraineeById(traineeId);

        assertEquals(trainee, foundTrainee);
    }

    @Test
    public void getAllTraineesTest() {
        List<Trainee> trainees = new ArrayList<>();
        when(traineeDAO.findAllTrainees()).thenReturn(trainees);

        List<Trainee> allTrainees = traineeService.getAllTrainees();

        assertEquals(trainees, allTrainees);
    }
}