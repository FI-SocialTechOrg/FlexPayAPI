package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ProductStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ProductStockResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IProductStockService {
    ApiResponse<ProductStockResponseDTO> getProductStockById(int id);
    ApiResponse<List<ProductStockResponseDTO>> getAllProductStocks();
    ApiResponse<ProductStockResponseDTO> createProductStock(ProductStockRequestDTO productStockRequestDTO);
    ApiResponse<ProductStockResponseDTO> updateProductStock(int id, ProductStockRequestDTO productStockRequestDTO);
    ApiResponse<Void> deleteProductStock(int id);
}
