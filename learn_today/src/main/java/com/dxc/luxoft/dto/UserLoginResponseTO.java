package com.dxc.luxoft.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserLoginResponseTO {
	private final String jwtToken;
	//private final String userType;

}
