package com.dxc.luxoft.service;

import org.springframework.stereotype.Service;

import com.dxc.luxoft.dto.StudentTO;
import com.dxc.luxoft.entities.Courses;
import com.dxc.luxoft.utill.ResponseTO;

@Service
public interface StudentService {
	
	public ResponseTO registerStudents(StudentTO stuTO);
	
	public Courses findCourseById(int id);
	
	
	
	

}
