package com.dxc.luxoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.luxoft.dto.PasswordResetTO;
import com.dxc.luxoft.dto.TrainerTO;
import com.dxc.luxoft.utill.ResponseTO;

@Service
public interface TrainerService {
	
	public ResponseTO registerTrainer(TrainerTO TrainerTo);
	
	public ResponseTO updatePassword(PasswordResetTO passwordResetTO, List<String> claims);
	
	

}
