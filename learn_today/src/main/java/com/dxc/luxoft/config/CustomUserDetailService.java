package com.dxc.luxoft.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dxc.luxoft.entities.UserDetail;
import com.dxc.luxoft.repositories.UserDetailsRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserDetailsRepository userDeatilsRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetail> user = userDeatilsRepo.findByUserName(username);
		
		return user.get(0);
	}

}
