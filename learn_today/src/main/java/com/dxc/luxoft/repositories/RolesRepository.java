package com.dxc.luxoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.luxoft.entities.Roles;

@Repository("rolesRepo")
public interface RolesRepository  extends JpaRepository<Roles, Integer>{

}
