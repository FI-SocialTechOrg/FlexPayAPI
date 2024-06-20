package com.flexpay.restapi.security.service;

import com.flexpay.restapi.security.model.request.LoginRequestDto;
import com.flexpay.restapi.security.model.request.RegisterRequestDto;
import com.flexpay.restapi.security.model.response.RegisteredUserResponseDto;
import com.flexpay.restapi.security.model.response.TokenResponseDto;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface AuthService {
    ApiResponse<RegisteredUserResponseDto> registerUser(RegisterRequestDto request);

    ApiResponse<TokenResponseDto> login(LoginRequestDto request);
}
