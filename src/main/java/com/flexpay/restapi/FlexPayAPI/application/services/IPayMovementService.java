package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayMovementRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayMovementResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IPayMovementService {
    ApiResponse<PayMovementResponseDTO> getPayMovementById(int id);
    ApiResponse<PayMovementResponseDTO> createPayMovement(PayMovementRequestDTO payMovementRequestDTO);
    ApiResponse<PayMovementResponseDTO> updatePayMovement(int id, PayMovementRequestDTO payMovementRequestDTO);
    ApiResponse<Void> deletePayMovement(int id);

}
