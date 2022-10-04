package com.dxc.luxoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.luxoft.entities.Courses;
import com.dxc.luxoft.utill.ResponseTO;

@Service
public interface CourseService{
	
	public ResponseTO saveCourses(Courses course, List<String> claims);
	//public String loginUser(UserLoginTO userLoginTO) throws Exception;
	//public boolean checkAccountExistance(String email);
	public List<Courses> getAllCourses( List<String> claims) throws Exception;
	public Courses findCourseById(int id,  List<String> claims);

}
