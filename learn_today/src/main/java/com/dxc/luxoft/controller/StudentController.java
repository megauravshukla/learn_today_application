package com.dxc.luxoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.luxoft.dto.StudentTO;
import com.dxc.luxoft.entities.Courses;
import com.dxc.luxoft.service.StudentService;
import com.dxc.luxoft.utill.ResponseTO;

@RestController
@RequestMapping("/stu")
public class StudentController {
	
	@Autowired
	private StudentService stuService;
	
	@PostMapping(value = "/register/students")
	public ResponseEntity<ResponseTO> saveTrainers(@RequestBody StudentTO stu) {
		ResponseTO res = stuService.registerStudents(stu);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(value = "/get/course")
	public ResponseEntity<Courses> getCourseById(@RequestParam(value = "id") int id) {
		Courses res = stuService.findCourseById(id);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(value = "/delete/student")
	public ResponseEntity<ResponseTO> deleteStudentById(@RequestParam(value = "id") int id) {
		ResponseTO res = stuService.deleteStudentById(id);
		return ResponseEntity.ok(res);
	}
	
	

}
