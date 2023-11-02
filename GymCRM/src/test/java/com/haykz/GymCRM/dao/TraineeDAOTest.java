package com.haykz.GymCRM.dao;

import com.haykz.GymCRM.dao.impl.TraineeDAOImpl;
import com.haykz.GymCRM.model.Trainee;
import com.haykz.GymCRM.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TraineeDAOTest {

    @InjectMocks
    private TraineeDAOImpl traineeDAO;

    @Mock
    private InMemoryStorage storage;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTraineeTest() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        Map<String, Object> storageMap = new HashMap<>();

        // Initialize custom lists for trainees and trainers
        List<Trainee> customTrainees = new ArrayList<>();
        customTrainees.add(createNewTrainee());
        storageMap.put("trainees", customTrainees);
        when(storage.getStorage()).thenReturn(storageMap);

        Trainee savedTrainee = traineeDAO.saveTrainee(trainee);

        assertNotNull(savedTrainee.getId());
    }

    @Test
    public void updateTraineeTest() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        Map<String, Object> storageMap = new HashMap<>();

        List<Trainee> customTrainees = new ArrayList<>();
        customTrainees.add(createNewTrainee());
        storageMap.put("trainees", customTrainees);
        when(storage.getStorage()).thenReturn(storageMap);

        Trainee updatedTrainee = traineeDAO.updateTrainee(trainee);

        assertEquals(trainee, updatedTrainee);
        verify(storage, times(2)).getStorage();
    }

    @Test
    public void findTraineeByIdTest() {
        Map<String, Object> storageMap = new HashMap<>();
        Trainee trainee = createNewTrainee();
        List<Trainee> customTrainees = new ArrayList<>();
        customTrainees.add(createNewTrainee());
        storageMap.put("trainees", customTrainees);
        when(storage.getStorage()).thenReturn(storageMap);

        Trainee foundTrainee = traineeDAO.findTraineeById(1L);

        assertEquals(trainee, foundTrainee);
    }

    @Test
    public void findAllTraineesTest() {
        Trainee trainee1 = new Trainee();
        Trainee trainee2 = new Trainee();
        trainee1.setId(1L);
        trainee2.setId(2L);
        Map<String, Object> storageMap = new HashMap<>();
        List<Trainee> customTrainees = new ArrayList<>();
        customTrainees.add(trainee1);
        customTrainees.add(trainee2);
        storageMap.put("trainees", customTrainees);
        when(storage.getStorage()).thenReturn(storageMap);

        List<Trainee> allTrainees = traineeDAO.findAllTrainees();

        assertEquals(2, allTrainees.size());
    }

    @Test
    public void deleteTraineeByIdTest() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);

        // Mock the storage
        Map<String, Object> storageMap = new HashMap<>();
        List<Trainee> traineesList = new ArrayList<>();
        traineesList.add(trainee);
        storageMap.put("trainees", traineesList);

        when(storage.getStorage()).thenReturn(storageMap);

        traineeDAO.deleteTraineeById(1L);

        verify(storage, times(2)).getStorage();
    }

    private Trainee createNewTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setAddress("Trainee Address");
        trainee.setDateOfBirth(new Date());
        return trainee;
    }
}
