package com.dxc.luxoft.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.luxoft.dto.AdminTO;
import com.dxc.luxoft.entities.Admin;
import com.dxc.luxoft.entities.UserDetail;
import com.dxc.luxoft.repositories.AdminRepository;
import com.dxc.luxoft.repositories.RolesRepository;
import com.dxc.luxoft.repositories.UserDetailsRepository;
import com.dxc.luxoft.service.AdminSevice;
import com.dxc.luxoft.utill.CommonConstants;
import com.dxc.luxoft.utill.ResponseTO;

import io.jsonwebtoken.lang.Strings;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminSeviceImpl implements AdminSevice {

	@Autowired
	private AdminRepository adminDAO;

	@Autowired
	private UserDetailsRepository userDeatilsDAO;

	@Autowired
	private RolesRepository rolesDAO;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public ResponseTO registerAdmin(AdminTO adminTO, List<String> claims) {
		ResponseTO response = new ResponseTO();
		try {
			if (claims.contains(CommonConstants.ADMIN_ROLE_NAME)) {
				if (checkAccountExistance(adminTO.getAdminEmail())) {
					Admin admin = new Admin();
					admin.setAdminName(adminTO.getAdminName());
					admin.setAdminEmail(adminTO.getAdminEmail());
					admin.setQualification(adminTO.getQualification());
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDateTime now = LocalDateTime.now();
					System.out.println(dtf.format(now));
				 	admin.setRegisterdate(dtf.format(now));
					adminDAO.save(admin);
					UserDetail userDetails = new UserDetail();
					userDetails.setUserName(adminTO.getAdminEmail());
					userDetails.setPassword(encoder.encode(adminTO.getPassword()));
					userDetails.setUserType(CommonConstants.ADMIN_USER_TYPE);
					userDetails.setRoles(adminTO.getRoles());
					userDeatilsDAO.save(userDetails);
					response.setResponse("Admin Registered Successfully...");
					response.setStatus(HttpServletResponse.SC_OK);
				} else {
					response.setResponse("User with email  " + adminTO.getAdminEmail() + "  is already registerd");
					response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				}
			} else {
				response.setResponse("Only Admins are authorised to register another admins...");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		} catch (Exception e) {
			log.error("Error while Registering the admin...");
			response.setResponse("Error while Registering the admin");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return response;
	}

	public boolean checkAccountExistance(String userName) {
		List<UserDetail> users = userDeatilsDAO.findByUserName(userName);
		if (users.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}
