package com.cts.outreach.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.outreach.exception.UserNotFoundException;
import com.cts.outreach.model.User;
import com.cts.outreach.service.UserAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RefreshScope
@RequestMapping(value = "/api/auth")
public class UserAuthenticationController {
	static final long EXPIRATIONTIME = 300000;
	

	private UserAuthenticationService userAuthenticationService;

	public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
		this.userAuthenticationService = userAuthenticationService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody User user) {
		HttpStatus status;
		String token = "";
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			User lUser = userAuthenticationService.findByUserIdAndUserPassword(user.getUserId(), user.getUserPassword());
			token = getToken(user.getUserId(), user.getUserPassword());
			map.put("token", token);
			map.put("user", lUser);
			status = HttpStatus.OK;
		} catch (UserNotFoundException e) {
			status = HttpStatus.UNAUTHORIZED;
		} catch (Exception e) {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	// Generate JWT token
	public String getToken(String username, String password) throws Exception {

		String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return jwtToken;

	}
}
