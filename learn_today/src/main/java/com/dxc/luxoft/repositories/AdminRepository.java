package com.dxc.luxoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.luxoft.entities.Admin;

@Repository("adminDAO")
public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
