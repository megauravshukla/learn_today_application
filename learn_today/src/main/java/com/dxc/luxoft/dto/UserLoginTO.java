package com.dxc.luxoft.dto;

import javax.persistence.Column;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginTO {
	
	private String email;
	
	private String password;

}
