package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StateCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StateCardResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IStateCardService {
    ApiResponse<StateCardResponseDTO> getStateCardById(int id);
    ApiResponse<StateCardResponseDTO> createStateCard(StateCardRequestDTO stateCardRequestDTO);
    ApiResponse<StateCardResponseDTO> updateStateCard(int id, StateCardRequestDTO stateCardRequestDTO);
    ApiResponse<Void> deleteStateCard(int id);
}
