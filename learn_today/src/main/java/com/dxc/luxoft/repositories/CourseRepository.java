package com.dxc.luxoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.luxoft.entities.Courses;

@Repository("courseDAO")
public interface CourseRepository extends JpaRepository<Courses, Integer>{
	
	public List<Courses> findByTitle(String title);

}
