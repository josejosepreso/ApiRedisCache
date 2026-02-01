package com.indianpharma.catalog.dto;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
public final class UserRegisterRequestDto {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final boolean active;
}
