package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingStateRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingStateResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IShoppingStateService {
    ApiResponse<ShoppingStateResponseDTO> getShoppingStateById(int id);
    ApiResponse<ShoppingStateResponseDTO> createShoppingState(ShoppingStateRequestDTO shoppingStateRequestDTO);
    ApiResponse<ShoppingStateResponseDTO> updateShoppingState(int id, ShoppingStateRequestDTO shoppingStateRequestDTO);
    ApiResponse<Void> deleteShoppingState(int id);

}
