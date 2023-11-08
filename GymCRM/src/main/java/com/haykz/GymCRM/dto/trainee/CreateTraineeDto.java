package com.haykz.GymCRM.dto.trainee;

import com.haykz.GymCRM.dto.user.CreateUserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class CreateTraineeDto {
    private Date dateOfBirth;
    private String address;
    @NotBlank
    private CreateUserDto user;
}
