package com.dxc.luxoft.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.luxoft.dto.TrainerTO;
import com.dxc.luxoft.entities.Roles;
import com.dxc.luxoft.entities.Trainer;
import com.dxc.luxoft.entities.UserDetail;
import com.dxc.luxoft.repositories.RolesRepository;
import com.dxc.luxoft.repositories.TrainerRepository;
import com.dxc.luxoft.repositories.UserDetailsRepository;
import com.dxc.luxoft.service.TrainerService;
import com.dxc.luxoft.utill.CommonConstants;
import com.dxc.luxoft.utill.ResponseTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerRepository trainerRepo;

	@Autowired
	private UserDetailsRepository userDeatilsRepo;

	@Autowired
	private RolesRepository rolesRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public ResponseTO registerTrainer(TrainerTO trainerTo) {
		ResponseTO response = new ResponseTO();
		try {
			Trainer trainer = new Trainer();
			trainer.setTrainerName(trainerTo.getTrainerName());
			trainer.setTrainerEmail(trainerTo.getTrainerEmail());
			trainer.setQualification(trainerTo.getQualification());
			trainer.setExpertise(trainerTo.getExpertise());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			trainer.setRegisterdate(dtf.format(now));
			trainerRepo.save(trainer);
			UserDetail userDetails = new UserDetail();
			userDetails.setUserName(trainerTo.getTrainerEmail());
			userDetails.setPassword(encoder.encode(trainerTo.getPassword()));
			userDetails.setUserType(CommonConstants.TRAINER_USER_TYPE);
			userDetails.setRoles(trainerTo.getRoles());
			userDeatilsRepo.save(userDetails);
			response.setResponse("Trainer Registered Successfully...");
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			log.error("Error wjile Registering the trainer...");
			response.setResponse("Error while Registering the trainer");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

	@Override
	@Transactional
	public ResponseTO updatePassword(String userName, String oldPassword, String newPassword) {
		ResponseTO response = new ResponseTO();
		try {
			UserDetail userDetails = userDeatilsRepo.findByUserName(userName).get(0);
			String encodedPassword = userDetails.getPassword();
			if (doPasswordsMatch(oldPassword, encodedPassword)) {
				userDeatilsRepo.updatePassword(newPassword, userName);
				response.setResponse("Password updated Successfully...");
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setResponse("Old Password is not matching...");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		} catch (Exception e) {
			log.error("Error wjile Registering the trainer...");
			response.setResponse("Error while updating the Pass");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

	public Boolean doPasswordsMatch(String UserProvidedOldPassword, String encodedOldPassword) {
		return encoder.matches(UserProvidedOldPassword, encodedOldPassword);
	}

}
