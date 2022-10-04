package com.dxc.luxoft.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.luxoft.config.CustomUserDetailService;
import com.dxc.luxoft.config.JWTConfig;
import com.dxc.luxoft.entities.Courses;
import com.dxc.luxoft.repositories.CourseRepository;
import com.dxc.luxoft.service.CourseService;
import com.dxc.luxoft.utill.CommonConstants;
import com.dxc.luxoft.utill.ResponseTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

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
	public ResponseTO saveCourses(Courses course, List<String> claims) {
		ResponseTO response = new ResponseTO();
		try {
			if (claims.contains(CommonConstants.ADMIN_ROLE_NAME)) {
				courseRepo.save(course);
				response.setResponse("Course Registered Successfully...");
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setResponse("Only Admins are authorised to register New Courses...");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		} catch (Exception e) {
			log.error("Error while Registering the trainer...");
			response.setResponse("Error while Registering the Course");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

//	@Override
//	public String loginUser(UserLoginTO userLoginTO) throws Exception {
//		String msg = "";
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(userLoginTO.getEmail(), userLoginTO.getPassword()));
//		} catch (BadCredentialsException e) {
//			throw new Exception("invalid user name or password ...", e);
//		}
//		final UserDetails userDetails = customUserDetailService.loadUserByUsername(userLoginTO.getEmail());
//		final String jwtToken = jwtConfig.generateToken(userDetails);
//		return jwtToken;
//	}

	@Override
	public List<Courses> getAllCourses(List<String> claims) throws Exception {
		List<Courses> response = new ArrayList<>();
		try {
			if (claims.contains(CommonConstants.ADMIN_ROLE_NAME)
					|| claims.contains(CommonConstants.STUDENT_ROLE_NAME)) {
				response = courseRepo.findAll();
			}
		} catch (Exception e) {
			log.error("Error while getting all the course...");
		}
		return response;

	}

	@Override
	public Courses findCourseById(int id, List<String> claims) {
		Optional<Courses> courseList = null;
		Courses courses = null;
		try {
			if (claims.contains(CommonConstants.ADMIN_ROLE_NAME)
					|| claims.contains(CommonConstants.STUDENT_ROLE_NAME)) {
				courseList = courseRepo.findById(id);
				if (courseList.isPresent()) {
					courses = courseList.get();
				}
			}
		} catch (Exception e) {
			log.error("Error While retriving courses by id...");
		}
		return courses;
	}

}
