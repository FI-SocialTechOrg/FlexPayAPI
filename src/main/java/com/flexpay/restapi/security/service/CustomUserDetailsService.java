package com.flexpay.restapi.security.service;

import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IAccountRepository accountRepository;

    public CustomUserDetailsService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        //busca al usuario por su username o email
        var account = accountRepository.findByEmailOrUserName(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró al usuario con el username o email: " + usernameOrEmail));

        //crea y retorna un objeto que representa al usuario autenticado
        return User.withUsername(account.getEmail())
                .password(account.getPassword())
                .build();
    }
}
