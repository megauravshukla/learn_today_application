package com.dxc.luxoft.dto;

import java.util.Date;
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
public class TrainerTO {
	
	public String trainerName;
	
	public String trainerEmail;
	
	public String password;

	public String expertise;
	
	public String qualification;
	
	public Date registerdate;
	
	private Set<Roles> roles;

}
