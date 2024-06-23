package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingCartRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingCartResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IShoppingCartService {
    ApiResponse<ShoppingCartResponseDTO> getShoppingCartById(int id);
    ApiResponse<ShoppingCartResponseDTO> getShoppingCartByAccountId(int accountId);
    ApiResponse<ShoppingCartResponseDTO> createShoppingCart(ShoppingCartRequestDTO shoppingCartRequestDTO);
    ApiResponse<ShoppingCartResponseDTO> updateShoppingCart(int id, ShoppingCartRequestDTO shoppingCartRequestDTO);
    ApiResponse<Void> deleteShoppingCart(int id);
}
