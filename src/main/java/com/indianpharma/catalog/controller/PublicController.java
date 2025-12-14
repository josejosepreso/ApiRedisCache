package com.indianpharma.catalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/")
public class PublicController {

	@GetMapping
	public String home() {
		return "Hello, world!";
	}

	@GetMapping("/health")
	public String health() {
		return "healthy";
	}
}
