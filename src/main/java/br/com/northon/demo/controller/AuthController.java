package br.com.northon.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.northon.demo.data.vo.security.AccountCredentialsVO;
import br.com.northon.demo.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthServices authServices;
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		if (checkIfParamsIsNull(data)) 
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Invalid client request!");
	
		var token = authServices.signin(data);
		
		if(token == null) 
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Invalid client request!");
		return token;
	}
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Refresh token for an authenticated user and returns a token")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refreshToken(@PathVariable("username") String userName, 
			@RequestHeader("Authorization") String refreshToken) {
		
		if (checkIfRefreshParamsIsNull(userName, refreshToken)) 
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Invalid client request!");
		
		var token = authServices.refreshToken(userName, refreshToken);
		
		if(token == null) 
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Invalid client request!");
		return token;
	}

	private boolean checkIfRefreshParamsIsNull(String userName, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank() || userName == null || userName.isBlank();
	}

	private boolean checkIfParamsIsNull(AccountCredentialsVO data) {
		return data == null || data.getUsername() == null
				|| data.getUsername().isBlank() || data.getPassword() == null
						|| data.getPassword().isBlank();
	}
}
