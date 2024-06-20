package com.flexpay.restapi.security.model.response;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserResponseDto {
    private int id;
    private String userName;
    private String email;
    private Role role;
}
