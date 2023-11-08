package com.haykz.GymCRM.repository;

import com.haykz.GymCRM.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    List<Trainee> getAllTraineesByUsername(String username);
}
