package com.dxc.luxoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.luxoft.dto.UserLoginTO;
import com.dxc.luxoft.entities.Courses;
import com.dxc.luxoft.service.CourseService;
import com.dxc.luxoft.utill.ResponseTO;
import com.springboot.security.DTOs.UserLoginResponseTO;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping(value = "/register/course")
	public ResponseEntity<ResponseTO> saveTrainers(@RequestBody Courses courses) {
		ResponseTO res = courseService.saveCourses(courses);
		return ResponseEntity.ok(res);
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<UserLoginResponseTO> authenticateUser(@RequestBody UserLoginTO user) throws Exception {
		String res = courseService.loginUser(user);
		return ResponseEntity.ok(new UserLoginResponseTO(res));
	}

}
