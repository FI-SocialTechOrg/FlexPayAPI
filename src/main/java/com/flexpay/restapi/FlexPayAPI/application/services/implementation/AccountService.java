package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.AccountRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.AccountResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IAccountService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Account;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IAccountRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IRoleRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountService implements IAccountService {
    private final IAccountRepository accountRepository;
    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public AccountService(IAccountRepository accountRepository, IRoleRepository roleRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<AccountResponseDTO> getAccountById(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()){
            Account account = accountOptional.get();
            AccountResponseDTO responseDTO = modelMapper.map(account, AccountResponseDTO.class);
            return new ApiResponse<>("Account fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Account not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<AccountResponseDTO> createAccount(AccountRequestDTO accountRequestDTO) {
        var account = modelMapper.map(accountRequestDTO, Account.class);
        account.setRole(roleRepository.getRoleById(accountRequestDTO.getRole()));
        accountRepository.save(account);
        var response = modelMapper.map(account, AccountResponseDTO.class);

        return new ApiResponse<>("Account created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<AccountResponseDTO> updateAccount(int id, AccountRequestDTO accountRequestDTO) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            return new ApiResponse<>("Account not found", Estatus.ERROR, null);
        }else {
            Account account = accountOptional.get();
            modelMapper.map(accountRequestDTO, account);
            account.setRole(roleRepository.getRoleById(accountRequestDTO.getRole()));
            accountRepository.save(account);
            AccountResponseDTO response = modelMapper.map(account, AccountResponseDTO.class);
            return new ApiResponse<>("Account updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteAccount(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            return new ApiResponse<>("Account not found", Estatus.ERROR, null);
        }else {
            accountRepository.deleteById(id);
            return new ApiResponse<>("Account deleted successfully", Estatus.SUCCESS, null);
        }
    }


}
