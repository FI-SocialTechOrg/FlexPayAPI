package com.flexpay.restapi.security.service;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Account;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IAccountRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IRoleRepository;
import com.flexpay.restapi.security.jwt.provider.JwtTokenProvider;
import com.flexpay.restapi.security.model.request.LoginRequestDto;
import com.flexpay.restapi.security.model.request.RegisterRequestDto;
import com.flexpay.restapi.security.model.response.RegisteredUserResponseDto;
import com.flexpay.restapi.security.model.response.TokenResponseDto;
import com.flexpay.restapi.shared.exception.CustomException;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final IAccountRepository accountRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager, IAccountRepository accountRepository, IRoleRepository roleRepository,PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public ApiResponse<RegisteredUserResponseDto> registerUser(RegisterRequestDto request) {
        //si el email ya está registrado
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(HttpStatus.CONFLICT, "The email '" + request.getEmail() + "' is already registered, please try another one");
        }
        //si el User Name ya está registrado
        if (accountRepository.existsByUserName(request.getUserName())) {
            throw new CustomException(HttpStatus.CONFLICT, "The username '" + request.getUserName() + "' is already registered, please try another one");
        }

        //si no existe, lo registra
        var account = Account.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.getRoleById(request.getRole()))
                .build();

        //guarda el usuario
        var newUser = accountRepository.save(account);

        //mapea de la entidad al dto
        var responseData = modelMapper.map(newUser, RegisteredUserResponseDto.class);

        return new ApiResponse<>("Register Success", Estatus.SUCCESS, responseData);
    }

    @Override
    public ApiResponse<TokenResponseDto> login(LoginRequestDto request) {
        //se validan las credenciales
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        //establece la seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //se obtiene el token
        String token = jwtTokenProvider.generateToken(authentication);

        Optional<Account> account = accountRepository.findByEmailOrUserName(request.getUsernameOrEmail(),request.getUsernameOrEmail());

        var responseData = new TokenResponseDto(account.get().getId(), token);
        return new ApiResponse<>("Authentication Success", Estatus.SUCCESS, responseData);
    }
}
