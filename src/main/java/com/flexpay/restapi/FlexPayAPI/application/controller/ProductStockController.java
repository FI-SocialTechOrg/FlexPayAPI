package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ProductStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ProductStockResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ProductStockWithStoreResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.ProductStockService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Stock", description = "Product Stock API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class ProductStockController {

    private final ProductStockService productStockService;

    public ProductStockController(ProductStockService productStockService) {
        this.productStockService = productStockService;
    }

    @Operation(summary = "get a product stock by id")
    @GetMapping("/product-stock/{id}")
    public ResponseEntity<ApiResponse<ProductStockResponseDTO>> getProductStockById(@PathVariable("id") int id) {
        ApiResponse<ProductStockResponseDTO> response = productStockService.getProductStockById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "get all product stocks")
    @GetMapping("/product-stock")
    public ResponseEntity<ApiResponse<List<ProductStockResponseDTO>>> getAllProductStocks() {
        var res = productStockService.getAllProductStocks();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new product stock")
    @PostMapping("/product-stock")
    public ResponseEntity<ApiResponse<ProductStockResponseDTO>> createProductStock(@RequestBody ProductStockRequestDTO productStockRequestDTO) {
        var res = productStockService.createProductStock(productStockRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing product stock")
    @PutMapping("/product-stock/{id}")
    public ResponseEntity<ApiResponse<ProductStockResponseDTO>> updateProductStock(@PathVariable int id, @RequestBody ProductStockRequestDTO productStockRequestDTO) {
        var res = productStockService.updateProductStock(id, productStockRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a product stock")
    @DeleteMapping("/product-stock/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductStock(@PathVariable int id) {
        var res = productStockService.deleteProductStock(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
