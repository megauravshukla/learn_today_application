package com.dxc.luxoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.luxoft.dto.AdminTO;
import com.dxc.luxoft.utill.ResponseTO;

@Service
public interface AdminSevice {
	
	public ResponseTO registerAdmin(AdminTO adminTO, List<String> claims);

}
