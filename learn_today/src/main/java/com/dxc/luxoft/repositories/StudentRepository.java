package com.dxc.luxoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.luxoft.entities.Students;

@Repository("stuRepo")
public interface StudentRepository extends JpaRepository<Students, Integer>{

}
