package com.indianpharma.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class UserRegisterRequestDto {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final boolean active;
}
