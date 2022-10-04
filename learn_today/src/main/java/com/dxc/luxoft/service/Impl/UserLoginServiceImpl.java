package com.dxc.luxoft.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dxc.luxoft.config.CustomUserDetailService;
import com.dxc.luxoft.config.JWTConfig;
import com.dxc.luxoft.dto.UserLoginTO;
import com.dxc.luxoft.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JWTConfig jwtConfig;


	@Override
	public String loginUser(UserLoginTO userLoginTO) throws Exception {
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

}
