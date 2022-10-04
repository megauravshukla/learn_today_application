package com.dxc.luxoft.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.luxoft.dto.AdminTO;
import com.dxc.luxoft.service.AdminSevice;
import com.dxc.luxoft.utill.ExtractClaims;
import com.dxc.luxoft.utill.ResponseTO;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ExtractClaims extractClaims;
	
	@Autowired
	private AdminSevice adminSevice;
	
	@PostMapping(value = "/register/admin")
	public ResponseEntity<ResponseTO> saveAdmins(@RequestBody AdminTO adminTO, @RequestHeader Map<String, String> headers) {
		List<String> claims = extractClaims.extractClaimsFromJWTToken(headers);
		ResponseTO res = adminSevice.registerAdmin(adminTO,claims );
		return ResponseEntity.ok(res);
	}

}
