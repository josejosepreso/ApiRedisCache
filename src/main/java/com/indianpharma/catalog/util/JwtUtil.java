package com.indianpharma.catalog.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.indianpharma.catalog.Configuration;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret.key}") String secretKeyStr) {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyStr.getBytes(StandardCharsets.UTF_8));
    }

	public String generateToken(String firstName, String lastName, String email, boolean active, boolean admin) throws Exception {
	    return Jwts.builder()
            .subject(email)
            .claim("firstName", firstName)
            .claim("lastName", lastName)
            .claim("active", active)
            .claim("admin", admin)
	    	.issuedAt(new Date())
	    	.expiration(Date.from(Instant.now().plus(Configuration.JWT_EXPIRATION_DURATION)))
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

	public String extractUserRole(String token) {
		try { return this.getTokenPayload(token).get("role").toString(); }
		catch(Exception e) { return ""; }
	}

	public boolean isValid(String token) {
		try{ return this.getTokenPayload(token).getExpiration().after(new Date()); }
		catch(Exception e) { return false; }
	}
}
