package com.haykz.GymCRM.repository;

import com.haykz.GymCRM.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    List<Trainer> getAllTrainersByUsername(String username);
}
