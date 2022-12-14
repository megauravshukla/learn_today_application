package com.dxc.luxoft.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTConfig {
	private String SECRET_KEY = "DXC_LUXOFT";
	
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
		
	}
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
			}
	
	public String generateToken(UserDetails userDetails){
		Map<String, Object> claims = new HashMap();
		//Adding Roles Information to JWTToken
		List<String> roleList = new ArrayList<String>();
		userDetails.getAuthorities().forEach(i->{
			roleList.add(String.valueOf(i));
		});
		claims.put("Roles", roleList);
		//
		return createToken(claims, userDetails.getUsername());
	}
	
			
	private String createToken (Map<String, Object> claims, String subject) {
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*5))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUsername(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}

}
