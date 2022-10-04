package com.dxc.luxoft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.luxoft.entities.Courses;
import com.dxc.luxoft.service.CourseService;
import com.dxc.luxoft.utill.ExtractClaims;
import com.dxc.luxoft.utill.ResponseTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ExtractClaims extractClaims;

	@PostMapping(value = "/register/course")
	public ResponseEntity<ResponseTO> saveTrainers(@RequestBody Courses courses,@RequestHeader Map<String, String> headers) {
		List<String> claims = extractClaims.extractClaimsFromJWTToken(headers);
		ResponseTO res = courseService.saveCourses(courses, claims);
		return ResponseEntity.ok(res);
	}

//	@PostMapping(path = "/login")
//	public ResponseEntity<UserLoginResponseTO> authenticateUser(@RequestBody UserLoginTO user) throws Exception {
//		String res = courseService.loginUser(user);
//		return ResponseEntity.ok(new UserLoginResponseTO(res));
//	}

	@GetMapping(value = "/get/all/courses")
	public ResponseEntity<List<Courses>> getAllCourses(@RequestHeader Map<String, String> headers) throws Exception {
		List<String> claims = extractClaims.extractClaimsFromJWTToken(headers);
		return ResponseEntity.ok(courseService.getAllCourses(claims));

	}
	
	@GetMapping(value = "/get/course/by/id")
	public ResponseEntity<Courses> getCourseById(@RequestParam(value = "id") int id, @RequestHeader Map<String, String> headers) {
		List<String> claims = extractClaims.extractClaimsFromJWTToken(headers);
		Courses res = courseService.findCourseById(id, claims);
		return ResponseEntity.ok(res);
	}

}
