package com.indianpharma.catalog.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.indianpharma.catalog.dto.UserRegisterRequestDto;
import com.indianpharma.catalog.dto.UserRegisterResponseDto;

@Component
public class AuthService {

    @Autowired
    private FirebaseService firebaseService;

	@Autowired
	private Database database;

    public UserRegisterResponseDto register(UserRegisterRequestDto dto) throws FirebaseAuthException, SQLException {
        final UserRecord userRecord = this.firebaseService.registerUserFirebase(dto.getEmail(), dto.getPassword());

        final String sql = "EXEC users.USER_INSERT \r\n" +
            "'" + userRecord.getEmail() + "',\r\n" +
            "'" + dto.getFirstName() + "',\r\n" +
            "'" + dto.getLastName() + "',\r\n" +
            String.valueOf(dto.isActive() ? 1 : 0);

		final ResultSet rs = this.database.executeQuery(sql);

        rs.next();

        assert rs.isLast();

        final int userId = rs.getInt("user_id");

        return new UserRegisterResponseDto(userId);
    }
}