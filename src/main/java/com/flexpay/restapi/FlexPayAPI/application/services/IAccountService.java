package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.AccountRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.AccountResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IAccountService {

    ApiResponse<AccountResponseDTO> getAccountById(int id);
    ApiResponse<AccountResponseDTO> createAccount(AccountRequestDTO accountRequestDTO);
    ApiResponse<AccountResponseDTO> updateAccount(int id, AccountRequestDTO accountRequestDTO);
    ApiResponse<Void> deleteAccount(int id);

}
