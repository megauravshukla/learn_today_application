package com.dxc.luxoft.service.Impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.luxoft.entities.Courses;
import com.dxc.luxoft.repositories.CourseRepository;
import com.dxc.luxoft.service.CourseService;
import com.dxc.luxoft.utill.ResponseTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public ResponseTO saveCourses(Courses course) {
		ResponseTO response = new ResponseTO();
		try {
			courseRepo.save(course);
			response.setResponse("Course Registered Successfully...");
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			log.error("Error wjile Registering the trainer...");
			response.setResponse("Error while Registering the Course");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

}
