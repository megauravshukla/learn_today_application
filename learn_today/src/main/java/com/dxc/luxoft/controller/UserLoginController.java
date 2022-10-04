package com.dxc.luxoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.luxoft.dto.UserLoginResponseTO;
import com.dxc.luxoft.dto.UserLoginTO;
import com.dxc.luxoft.service.UserLoginService;
import com.dxc.luxoft.utill.ExtractClaims;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/login")
@Slf4j
public class UserLoginController {
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private ExtractClaims extractClaims;

	@PostMapping(path = "/v1/user/login")
	public ResponseEntity<UserLoginResponseTO> authenticateUser(@RequestBody UserLoginTO user) throws Exception {
		String res = userLoginService.loginUser(user);
		return ResponseEntity.ok(new UserLoginResponseTO(res));
	}


}
