package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ProductRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ProductResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface IProductService {
    ApiResponse<ProductResponseDTO> getProductById(int id);
    ApiResponse<List<ProductResponseDTO>> getAllProducts();
    ApiResponse<ProductResponseDTO> createProduct(ProductRequestDTO productRequestDTO);
    ApiResponse<ProductResponseDTO> updateProduct(int id, ProductRequestDTO productRequestDTO);
    ApiResponse<Void> deleteProduct(int id);
}
