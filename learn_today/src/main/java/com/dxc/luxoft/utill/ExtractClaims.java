package com.dxc.luxoft.utill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.luxoft.config.JWTConfig;

import net.minidev.json.JSONObject;

@Component
public class ExtractClaims {

	@Autowired
	private JWTConfig jwtConfig;

	public List<String> extractClaimsFromJWTToken(Map<String, String> headers) {
		List<String> claims = new ArrayList<>();
		if (headers.keySet().contains("authorization")) {
			String bearerToken = headers.get("authorization");
			String token = bearerToken.substring(7, bearerToken.length());
			JSONObject json = new JSONObject(jwtConfig.extractAllClaims(token));
			if (json.containsKey("Roles")) {
				String abc = json.get("Roles").toString();
				String[] roles = abc.substring(1, abc.length() - 1).split(",");
				for (String role : roles) {
					claims.add(role);
				}
			}
		}
		return claims;
	}

}
