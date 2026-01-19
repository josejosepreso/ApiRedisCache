package com.indianpharma.catalog.controller;

import com.indianpharma.catalog.util.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indianpharma.catalog.dto.ProductCreateRequestDto;
import com.indianpharma.catalog.service.CatalogService;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/all")
	public ResponseEntity<?> catalog() {
		try {
			return ResponseEntity.ok(this.catalogService.getAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse(e.getMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductCreateRequestDto productCreateRequestDto) {
		try {
			return ResponseEntity.ok(this.catalogService.create(productCreateRequestDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse(e.getMessage()));
		}
	}
}
