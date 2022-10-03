package com.dxc.luxoft.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.luxoft.config.CustomUserDetailService;
import com.dxc.luxoft.config.JWTConfig;
import com.dxc.luxoft.dto.UserLoginTO;
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
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JWTConfig jwtConfig;

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
	
	@Override
	public String loginUser(UserLoginTO userLoginTO) throws Exception {
		String msg = "";
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLoginTO.getEmail(), userLoginTO.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("invalid user name or password ...", e);
		}
		final UserDetails userDetails = customUserDetailService.loadUserByUsername(userLoginTO.getEmail());
		final String jwtToken = jwtConfig.generateToken(userDetails);
		return jwtToken;
	}

	@Override
	public List<Courses> getAllCourses() {
		List<Courses> response = new ArrayList<>();
		try {
			response = courseRepo.findAll();
		} catch (Exception e) {
			log.error("Error wjile Registering the trainer...");
		}
		return response;

	}

	
}
