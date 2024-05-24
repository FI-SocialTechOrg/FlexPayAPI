package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingStockResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IShoppingStockService {
    ApiResponse<ShoppingStockResponseDTO> getShoppingStockById(int id);
    ApiResponse<List<ShoppingStockResponseDTO>> getAllShoppingStocks();
    ApiResponse<ShoppingStockResponseDTO> createShoppingStock(ShoppingStockRequestDTO shoppingStockRequestDTO);
    ApiResponse<ShoppingStockResponseDTO> updateShoppingStock(int id, ShoppingStockRequestDTO shoppingStockRequestDTO);
    ApiResponse<Void> deleteShoppingStock(int id);
}
