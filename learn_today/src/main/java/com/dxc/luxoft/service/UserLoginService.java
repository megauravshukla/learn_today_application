package com.dxc.luxoft.service;

import org.springframework.stereotype.Service;

import com.dxc.luxoft.dto.UserLoginTO;

@Service
public interface UserLoginService {
	
	public String loginUser(UserLoginTO userLoginTO) throws Exception;

}
