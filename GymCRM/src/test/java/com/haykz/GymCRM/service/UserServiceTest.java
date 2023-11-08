//package com.haykz.GymCRM.service;
//
//import com.haykz.GymCRM.model.Trainee;
//import com.haykz.GymCRM.model.Trainer;
//import com.haykz.GymCRM.model.TrainingType;
//import com.haykz.GymCRM.model.User;
//import com.haykz.GymCRM.storage.InMemoryStorage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//public class UserServiceTest {
//
//    @InjectMocks
//    private UserService userService;
//
//    @Mock
//    private InMemoryStorage storage;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//    @Test
//    public void createProfileTest() {
//        String firstName = "John";
//        String lastName = "Smith";
//
//        Map<String, Object> storageMap = createCustomStorageWithUsers();
//        when(storage.getStorage()).thenReturn(storageMap);
//
//
//        User createdUser = userService.createProfile(firstName, lastName);
//
//        assertNotNull(createdUser);
//        assertEquals(firstName, createdUser.getFirstName());
//        assertEquals(lastName, createdUser.getLastName());
//        assertTrue(createdUser.getUsername().matches("John\\.Smith\\.\\w{5}"));
//        assertEquals(12, createdUser.getPassword().length());
//    }
//
//    @Test
//    public void generateUsernameTest() {
//        String firstName = "John";
//        String lastName = "Smith";
//
//        Map<String, Object> storageMap = createCustomStorageWithUsers();
//        when(storage.getStorage()).thenReturn(storageMap);
//
//        UserService userService = new UserService(); // Create an instance of the service
//        ReflectionTestUtils.setField(userService, "storage", storage); // Set the private field
//
//        String username = (String) ReflectionTestUtils.invokeMethod(userService, "generateUsername", firstName, lastName);
//
//        assertTrue(username.matches("John\\.Smith\\.\\w{5}"));
//    }
//
//    @Test
//    public void generateRandomPasswordTest() {
//        int length = 12;
//
//        UserService userService = new UserService(); // Create an instance of the service
//
//        String password = (String) ReflectionTestUtils.invokeMethod(userService, "generateRandomPassword", length);
//
//        assertNotNull(password);
//        assertEquals(length, password.length());
//    }
//
//    @Test
//    public void checkForExistingUsersTest() {
//        String username = "John.Smith";
//
//        Map<String, Object> storageMap = createCustomStorageWithUsers();
//        when(storage.getStorage()).thenReturn(storageMap);
//
//        UserService userService = new UserService(); // Create an instance of the service
//        ReflectionTestUtils.setField(userService, "storage", storage); // Set the private field
//
//        List<Trainer> trainers = new ArrayList<>();
//        List<Trainee> trainees = new ArrayList<>();
//
//        Trainee trainee = new Trainee();
//        trainee.setId(1L);
//        User traineeUser = new User();
//        traineeUser.setUsername(username);
//        trainee.setUser(traineeUser);
//        trainees.add(trainee);
//
//        Trainer trainer = new Trainer();
//        trainer.setId(1L);
//        User trainerUser = new User();
//        trainerUser.setUsername(username);
//        trainer.setUser(trainerUser);
//        trainers.add(trainer);
//
//        boolean result = Boolean.TRUE.equals(ReflectionTestUtils.invokeMethod(userService, "checkForExistingUsers", username));
//
//        assertTrue(result);
//    }
//
//    public Map<String, Object> createCustomStorageWithUsers() {
//        Map<String, Object> customStorage = new HashMap<>();
//
//        // Create a list of trainers
//        List<Trainer> trainers = new ArrayList<>();
//        Trainer trainer1 = new Trainer();
//        trainer1.setSpecialization(new TrainingType());
//        User trainer1User = new User();
//        trainer1User.setUsername("John.Smith");
//        trainer1.setUser(trainer1User);
//        trainers.add(trainer1);
//
//        Trainer trainer2 = new Trainer();
//        trainer2.setSpecialization(new TrainingType());
//        User trainer2User = new User();
//        trainer2User.setUsername("trainer2_username");
//        trainer2.setUser(trainer2User);
//        trainers.add(trainer2);
//
//        // Create a list of trainees
//        List<Trainee> trainees = new ArrayList<>();
//        Trainee trainee1 = new Trainee();
//        User trainee1User = new User();
//        trainee1User.setUsername("trainee1_username");
//        trainee1.setUser(trainee1User);
//        trainees.add(trainee1);
//
//        Trainee trainee2 = new Trainee();
//        User trainee2User = new User();
//        trainee2User.setUsername("trainee2_username");
//        trainee2.setUser(trainee2User);
//        trainees.add(trainee2);
//
//        // Put the lists into the custom storage
//        customStorage.put("trainers", trainers);
//        customStorage.put("trainees", trainees);
//
//        return customStorage;
//    }
//}
//
