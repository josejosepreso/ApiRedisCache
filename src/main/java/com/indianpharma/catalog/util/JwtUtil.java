package com.indianpharma.catalog.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.indianpharma.catalog.Configuration;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private SecretKey secretKey;

	private Duration jwtDuration;

	public JwtUtil(@Value("${jwt.secret.key}") String secretKeyStr) {
		this.secretKey = Keys.hmacShaKeyFor(secretKeyStr.getBytes(StandardCharsets.UTF_8));
		this.jwtDuration = Duration.ofMinutes(Configuration.JWT_EXPIRATION_DURATION);
	}

	public String generateToken(String firstName, String lastName, String email, boolean active, boolean admin)
			throws Exception {
		return Jwts.builder()
				.subject(email)
				.claim("firstName", firstName)
				.claim("lastName", lastName)
				.claim("active", active)
				.claim("admin", admin)
				.issuedAt(new Date())
				.expiration(Date.from(Instant.now().plus(this.jwtDuration)))
				.signWith(this.secretKey)
				.compact();
	}

	private Claims getTokenPayload(String token) {
		return Jwts.parser()
				.verifyWith(this.secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	private boolean isStatus(String token, String status) {
		try {
			return this.getTokenPayload(token).get(status).toString().equals("true");
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isActive(String token) {
		return this.isStatus(token, "active");
	}

	public boolean isAdmin(String token) {
		return this.isStatus(token, "admin");
	}

	public boolean isValid(String token) {
		try {
			return this.getTokenPayload(token).getExpiration().after(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public Optional<String> getEmail(String token) {
		try {
			return Optional.of(this.getTokenPayload(token).getSubject());
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public List<GrantedAuthority> getAuthorities(String token) {
		if (!this.isValid(token)) {
			return Collections.emptyList();
		}

		final List<GrantedAuthority> authorities = new ArrayList<>();

		if (this.isActive(token))
			authorities.add(new SimpleGrantedAuthority("ROLE_ACTIVE"));
		if (this.isAdmin(token))
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return authorities;
	}
}
