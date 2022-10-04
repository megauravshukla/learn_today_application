package com.dxc.luxoft.dto;

import java.util.Set;

import com.dxc.luxoft.entities.Roles;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonSerialize
public class AdminTO {
	
	public String adminName;
	
	public String adminEmail;
	
	public String password;
	
	public String qualification;
	
	public String registerdate;
	
	private Set<Roles> roles;

}
