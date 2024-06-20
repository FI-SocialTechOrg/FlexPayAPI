package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CreditConfigurationRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CreditConfigurationResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface ICreditConfigurationService {
    ApiResponse<CreditConfigurationResponseDTO> getCreditConfigurationById(int id);
    ApiResponse<CreditConfigurationResponseDTO> createCreditConfiguration(CreditConfigurationRequestDTO creditConfigurationRequestDTO);
    ApiResponse<CreditConfigurationResponseDTO> updateCreditConfiguration(int id, CreditConfigurationRequestDTO creditConfigurationRequestDTO);
    ApiResponse<Void> deleteCreditConfiguration(int id);
}
