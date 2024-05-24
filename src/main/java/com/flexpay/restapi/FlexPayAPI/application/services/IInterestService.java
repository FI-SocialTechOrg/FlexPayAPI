package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.InterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.InterestResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IInterestService {
    ApiResponse<InterestResponseDTO> getInterestById(int id);
    ApiResponse<InterestResponseDTO> createInterest(InterestRequestDTO interestRequestDTO);
    ApiResponse<InterestResponseDTO> updateInterest(int id, InterestRequestDTO interestRequestDTO);
    ApiResponse<Void> deleteInterest(int id);
}
