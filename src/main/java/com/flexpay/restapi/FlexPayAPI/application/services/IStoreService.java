package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StoreRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StoreResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StoreWithoutProductStockResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IStoreService {
    ApiResponse<StoreResponseDTO> getStoreById(int id);
    ApiResponse<List<StoreResponseDTO>> getAllStores();
    ApiResponse<StoreResponseDTO> createStore(StoreRequestDTO storeRequestDTO);
    ApiResponse<StoreResponseDTO> updateStore(int id, StoreRequestDTO storeRequestDTO);
    ApiResponse<Void> deleteStore(int id);
}
