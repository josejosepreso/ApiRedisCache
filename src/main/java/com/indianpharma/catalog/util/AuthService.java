package com.indianpharma.catalog.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.indianpharma.catalog.Configuration;
import com.indianpharma.catalog.dto.UserLoginRequestDto;
import com.indianpharma.catalog.dto.UserLoginResponseDto;
import com.indianpharma.catalog.dto.UserRegisterRequestDto;
import com.indianpharma.catalog.dto.UserRegisterResponseDto;

@Component
public class AuthService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private FirebaseService firebaseService;

	@Autowired
	private Database database;

	@Value("${firebase.api.key}")
	private String firebaseApiKey;

	public UserRegisterResponseDto register(UserRegisterRequestDto dto) throws FirebaseAuthException, SQLException {
		final UserRecord userRecord = this.firebaseService.registerUserFirebase(dto.getEmail(), dto.getPassword());

		final String sql = String.format(
				"EXEC sch_users.USER_INSERT '%s', '%s', '%s', %s",
				userRecord.getEmail(),
				dto.getFirstName(),
				dto.getLastName(),
				String.valueOf(dto.isActive() ? 1 : 0));

		final ResultSet rs = this.database.executeQuery(sql);

		rs.next();

		assert rs.isLast();

		final int userId = rs.getInt("user_id");

		return new UserRegisterResponseDto(userId);
	}

	public UserLoginResponseDto login(UserLoginRequestDto userLogin) throws SQLException, Exception {
		RestClient.create(Configuration.FIREBASE_AUTH_URL)
				.post()
				.uri(uriBuilder -> uriBuilder
						.queryParam("key", this.firebaseApiKey)
						.build())
				.body(userLogin)
				.contentType(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(String.class);

		final String sql = String.format("""
				select email,
						first_name,
						last_name,
						active,
						admin
				from sch_users.users
				where email = '%s'
				""",
				userLogin.getEmail());

		final ResultSet rs = this.database.executeQuery(sql);

		rs.next();

		assert rs.isLast();

		final String email = rs.getString("email");
		final String firstName = rs.getString("first_name");
		final String lastName = rs.getString("last_name");
		final boolean isActive = rs.getBoolean("active");
		final boolean isAdmin = rs.getBoolean("admin");

		final String token = this.jwtUtil.generateToken(firstName, lastName, email, isActive, isAdmin);

		return new UserLoginResponseDto(token);
	}
}