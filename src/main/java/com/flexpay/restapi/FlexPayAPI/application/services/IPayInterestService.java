package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayInterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayInterestResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IPayInterestService {
    ApiResponse<PayInterestResponseDTO> getPayInterestById(int id);
    ApiResponse<PayInterestResponseDTO> createPayInterest(PayInterestRequestDTO payInterestRequestDTO);
    ApiResponse<PayInterestResponseDTO> updatePayInterest(int id, PayInterestRequestDTO payInterestRequestDTO);
    ApiResponse<Void> deletePayInterest(int id);
}
