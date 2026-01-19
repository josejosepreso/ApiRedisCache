package com.indianpharma.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indianpharma.catalog.dto.UserLoginRequestDto;
import com.indianpharma.catalog.dto.UserRegisterRequestDto;
import com.indianpharma.catalog.util.AuthService;
import com.indianpharma.catalog.util.ErrorResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
		try {
			return ResponseEntity.ok(this.authService.login(userLoginRequestDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
		try {
			return ResponseEntity.ok(this.authService.register(userRegisterRequestDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse(e.getMessage()));
		}
	}
}
