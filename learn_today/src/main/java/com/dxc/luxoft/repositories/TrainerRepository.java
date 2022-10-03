package com.dxc.luxoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.luxoft.entities.Trainer;

@Repository("trainerRepo")
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{

}
