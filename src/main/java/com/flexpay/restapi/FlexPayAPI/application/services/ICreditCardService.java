package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CreditCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CreditCardResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface ICreditCardService {
    ApiResponse<CreditCardResponseDTO> getCreditCardById(int id);
    ApiResponse<CreditCardResponseDTO> createCreditCard(CreditCardRequestDTO creditCardRequestDTO);
    ApiResponse<CreditCardResponseDTO> updateCreditCard(int id, CreditCardRequestDTO creditCardRequestDTO);
    ApiResponse<Void> deleteCreditCard(int id);
}
