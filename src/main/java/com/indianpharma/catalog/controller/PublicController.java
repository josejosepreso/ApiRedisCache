package com.indianpharma.catalog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PublicController {

	@GetMapping
	public ResponseEntity<?> home() {
		final Map<String, String> m = new HashMap<>();
		m.put("hello", "world");

		return ResponseEntity.ok(m);
	}

	@GetMapping("/health")
	public ResponseEntity<?> health() {
		final var m = new HashMap<>();
		m.put("status", "healthy");
		m.put("version", "0.0.1");

		return ResponseEntity.ok(m);
	}
}
