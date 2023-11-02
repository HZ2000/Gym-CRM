package com.haykz.GymCRM.dao;

import com.haykz.GymCRM.dao.impl.TrainingDAOImpl;
import com.haykz.GymCRM.model.Training;
import com.haykz.GymCRM.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TrainingDAOTest {

    @InjectMocks
    private TrainingDAOImpl trainingDAO;

    @Mock
    private InMemoryStorage storage;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTrainingTest() {
        Training training = new Training();
        training.setId(1L);
        Map<String, Object> storageMap = new HashMap<>();

        // Initialize custom lists for trainings
        List<Training> customTrainings = new ArrayList<>();
        customTrainings.add(createNewTraining());
        storageMap.put("trainings", customTrainings);
        when(storage.getStorage()).thenReturn(storageMap);

        Training savedTraining = trainingDAO.saveTraining(training);

        assertNotNull(savedTraining.getId());
    }

    @Test
    public void updateTrainingTest() {
        Training training = new Training();
        training.setId(1L);
        Map<String, Object> storageMap = new HashMap<>();

        List<Training> customTrainings = new ArrayList<>();
        customTrainings.add(createNewTraining());
        storageMap.put("trainings", customTrainings);
        when(storage.getStorage()).thenReturn(storageMap);

        Training updatedTraining = trainingDAO.updateTraining(training);

        assertEquals(training, updatedTraining);
        verify(storage, times(2)).getStorage();
    }

    @Test
    public void findTrainingByIdTest() {
        Map<String, Object> storageMap = new HashMap<>();
        Training training = createNewTraining();
        List<Training> customTrainings = new ArrayList<>();
        customTrainings.add(createNewTraining());
        storageMap.put("trainings", customTrainings);
        when(storage.getStorage()).thenReturn(storageMap);

        Training foundTraining = trainingDAO.findTrainingById(1L);

        assertEquals(training, foundTraining);
    }

    @Test
    public void findAllTrainingsTest() {
        Training training1 = new Training();
        Training training2 = new Training();
        training1.setId(1L);
        training2.setId(2L);
        Map<String, Object> storageMap = new HashMap<>();
        List<Training> customTrainings = new ArrayList<>();
        customTrainings.add(training1);
        customTrainings.add(training2);
        storageMap.put("trainings", customTrainings);
        when(storage.getStorage()).thenReturn(storageMap);

        List<Training> allTrainings = trainingDAO.findAllTrainings();

        assertEquals(2, allTrainings.size());
    }

    @Test
    public void deleteTrainingByIdTest() {
        Training training = new Training();
        training.setId(1L);

        // Mock the storage
        Map<String, Object> storageMap = new HashMap<>();
        List<Training> trainingsList = new ArrayList<>();
        trainingsList.add(training);
        storageMap.put("trainings", trainingsList);

        when(storage.getStorage()).thenReturn(storageMap);

        trainingDAO.deleteTrainingById(1L);

        verify(storage, times(2)).getStorage();
    }

    private Training createNewTraining() {
        Training training = new Training();
        training.setId(1L);
        training.setTrainingName("Training Name");
        return training;
    }
}
