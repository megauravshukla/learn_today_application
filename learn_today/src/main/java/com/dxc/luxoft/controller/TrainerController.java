package com.dxc.luxoft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.luxoft.dto.PasswordResetTO;
import com.dxc.luxoft.dto.TrainerTO;
import com.dxc.luxoft.service.TrainerService;
import com.dxc.luxoft.utill.ExtractClaims;
import com.dxc.luxoft.utill.ResponseTO;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private ExtractClaims extractClaims;

	@PostMapping(value = "/register/trainer")
	public ResponseEntity<ResponseTO> saveTrainers(@RequestBody TrainerTO trainerTo) {
		ResponseTO res = trainerService.registerTrainer(trainerTo);
		return ResponseEntity.ok(res);
	}
	
	@PostMapping(value = "/update/password")
	public ResponseEntity<ResponseTO> updatePassword(@RequestBody PasswordResetTO passwordResetTO, @RequestHeader Map<String, String> headers) {
		List<String> claims = extractClaims.extractClaimsFromJWTToken(headers);
		ResponseTO res = trainerService.updatePassword(passwordResetTO, claims);
		return ResponseEntity.ok(res);
	}

}
