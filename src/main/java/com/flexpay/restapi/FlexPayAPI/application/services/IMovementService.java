package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.MovementRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.MovementResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IMovementService {
    ApiResponse<MovementResponseDTO> getMovementById(int id);
    ApiResponse<MovementResponseDTO> createMovement(MovementRequestDTO movementRequestDTO);
    ApiResponse<MovementResponseDTO> updateMovement(int id, MovementRequestDTO movementRequestDTO);
    ApiResponse<Void> deleteMovement(int id);
}
