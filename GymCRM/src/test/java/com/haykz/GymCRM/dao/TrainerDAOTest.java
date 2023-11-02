package com.haykz.GymCRM.dao;

import com.haykz.GymCRM.dao.impl.TrainerDAOImpl;
import com.haykz.GymCRM.model.Trainer;
import com.haykz.GymCRM.model.TrainingType;
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

public class TrainerDAOTest {

    @InjectMocks
    private TrainerDAOImpl trainerDAO;

    @Mock
    private InMemoryStorage storage;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTrainerTest() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        Map<String, Object> storageMap = new HashMap<>();

        // Initialize custom lists for trainers
        List<Trainer> customTrainers = new ArrayList<>();
        customTrainers.add(createNewTrainer());
        storageMap.put("trainers", customTrainers);
        when(storage.getStorage()).thenReturn(storageMap);

        Trainer savedTrainer = trainerDAO.saveTrainer(trainer);

        assertNotNull(savedTrainer.getId());
    }

    @Test
    public void updateTrainerTest() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        Map<String, Object> storageMap = new HashMap<>();

        List<Trainer> customTrainers = new ArrayList<>();
        customTrainers.add(createNewTrainer());
        storageMap.put("trainers", customTrainers);
        when(storage.getStorage()).thenReturn(storageMap);

        Trainer updatedTrainer = trainerDAO.updateTrainer(trainer);

        assertEquals(trainer, updatedTrainer);
        verify(storage, times(2)).getStorage();
    }

    @Test
    public void findTrainerByIdTest() {
        Map<String, Object> storageMap = new HashMap<>();
        Trainer trainer = createNewTrainer();
        List<Trainer> customTrainers = new ArrayList<>();
        customTrainers.add(createNewTrainer());
        storageMap.put("trainers", customTrainers);
        when(storage.getStorage()).thenReturn(storageMap);

        Trainer foundTrainer = trainerDAO.findTrainerById(1L);

        assertEquals(trainer, foundTrainer);
    }

    @Test
    public void findAllTrainersTest() {
        Trainer trainer1 = new Trainer();
        Trainer trainer2 = new Trainer();
        trainer1.setId(1L);
        trainer2.setId(2L);
        Map<String, Object> storageMap = new HashMap<>();
        List<Trainer> customTrainers = new ArrayList<>();
        customTrainers.add(trainer1);
        customTrainers.add(trainer2);
        storageMap.put("trainers", customTrainers);
        when(storage.getStorage()).thenReturn(storageMap);

        List<Trainer> allTrainers = trainerDAO.findAllTrainers();

        assertEquals(2, allTrainers.size());
    }

    @Test
    public void deleteTrainerByIdTest() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);

        // Mock the storage
        Map<String, Object> storageMap = new HashMap<>();
        List<Trainer> trainersList = new ArrayList<>();
        trainersList.add(trainer);
        storageMap.put("trainers", trainersList);

        when(storage.getStorage()).thenReturn(storageMap);

        trainerDAO.deleteTrainerById(1L);

        verify(storage, times(2)).getStorage();
    }

    private Trainer createNewTrainer() {
        Trainer trainer = new Trainer();
        trainer.setId(1L);
        trainer.setSpecialization(new TrainingType());
        return trainer;
    }
}
