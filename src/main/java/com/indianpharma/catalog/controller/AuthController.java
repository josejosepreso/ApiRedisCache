package com.indianpharma.catalog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@PostMapping("/login")
	public String login() {
		return null;
	}

	@PostMapping("/signup")
	public String signup() {
		return null;
	}
}
