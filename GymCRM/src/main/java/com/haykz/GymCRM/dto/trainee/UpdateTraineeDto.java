package com.haykz.GymCRM.dto.trainee;

import com.haykz.GymCRM.dto.user.UpdateUserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateTraineeDto {
    private Date dateOfBirth;
    private String address;
    @NotBlank
    private UpdateUserDto user;
}
