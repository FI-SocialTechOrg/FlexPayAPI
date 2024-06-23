package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CapitalizationPeriodRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CapitalizationPeriodResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface ICapitalizationPeriodService {
    ApiResponse<CapitalizationPeriodResponseDTO> getCapitalizationPeriodById(int id);
    ApiResponse<CapitalizationPeriodResponseDTO> createCapitalizationPeriod(CapitalizationPeriodRequestDTO capitalizationPeriodRequestDTO);
    ApiResponse<CapitalizationPeriodResponseDTO> updateCapitalizationPeriod(int id, CapitalizationPeriodRequestDTO capitalizationPeriodRequestDTO);
    ApiResponse<Void> deleteCapitalizationPeriod(int id);
}
