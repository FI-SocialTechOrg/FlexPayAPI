package com.flexpay.restapi.security.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotEmpty(message = "The user name is required")
    private String userName;

    @NotEmpty(message = "The email is required")
    @Email(message = "email format is invalid")
    private String email;

    @NotEmpty(message = "The password is required")
    @Size(min = 3, message = "the password must be at least 3 characters long")
    private String password;

    @NotEmpty(message = "Role is required")
    private int role;
}
