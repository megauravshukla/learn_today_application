package com.dxc.luxoft.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dxc.luxoft.config.CustomUserDetailService;
import com.dxc.luxoft.config.JWTConfig;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JWTConfig jwtConfig;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String headerWithBearerTag = request.getHeader("Authorization");
		String userName =null;
		String jwtToken=null;
		if(headerWithBearerTag !=null && headerWithBearerTag.startsWith("Bearer ")) {
			jwtToken = headerWithBearerTag.substring(7);
			userName = jwtConfig.extractUsername(jwtToken);
		}
			if(userName != null && SecurityContextHolder.getContext().getAuthentication()== null) {
				UserDetails userDetails = this.customUserDetailService.loadUserByUsername(userName);
				if(jwtConfig.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);
		}
		
	}