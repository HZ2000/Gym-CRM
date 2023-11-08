package com.haykz.GymCRM.service.impl;

import com.haykz.GymCRM.dto.trainee.CreateTraineeDto;
import com.haykz.GymCRM.dto.trainee.UpdateTraineeDto;
import com.haykz.GymCRM.model.Trainee;
import com.haykz.GymCRM.model.User;
import com.haykz.GymCRM.service.TraineeService;
import com.haykz.GymCRM.service.UserService;
import com.haykz.GymCRM.validation.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Override
    public Trainee createTrainee(CreateTraineeDto traineeDto) {
        Trainee trainee = modelMapper.map(traineeDto, Trainee.class);
        User user = userService.createProfile(traineeDto.getUser().getFirstName(), traineeDto.getUser().getLastName());
        trainee.setUser(user);
        return trainee;
    }

    @Override
    public Trainee updateTrainee(UpdateTraineeDto traineeDto) {
        if(authenticationService.authenticate(traineeDto.getUser().getUsername(), traineeDto.getUser().getPassword()) {
            Trainee trainee = modelMapper.map(traineeDto, Trainee.class);
            User user = userService.createProfile(traineeDto.getUser().getFirstName(), traineeDto.getUser().getLastName());
            trainee.setUser(user);
            return trainee;
        }
    }

    @Override
    public void deleteTrainee(Long traineeId) {

    }

    @Override
    public Trainee getTraineeById(Long traineeId) {
        return null;
    }

    @Override
    public List<Trainee> getAllTrainees() {
        return null;
    }
}
