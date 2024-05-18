package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StateStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StateStockResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IStateStockService {
    ApiResponse<StateStockResponseDTO> getStateStocksbyId(int id);
    ApiResponse<StateStockResponseDTO> createStateStock(StateStockRequestDTO stateStockRequestDTO);
    ApiResponse<StateStockResponseDTO> updateStateStock(int id, StateStockRequestDTO stateStockRequestDTO);
    ApiResponse<Void> deleteStateStock(int id);
}
