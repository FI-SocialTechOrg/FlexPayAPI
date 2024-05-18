package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ProductRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ProductResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.ProductService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Product API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "get a product by id")
    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(@PathVariable("id") int id) {
        ApiResponse<ProductResponseDTO> response = productService.getProductById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "get all products")
    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAllProducts() {
        var res = productService.getAllProducts();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new product")
    @PostMapping("/product")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        var res = productService.createProduct(productRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing product")
    @PutMapping("/product/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(@PathVariable int id, @RequestBody ProductRequestDTO productRequestDTO) {
        var res = productService.updateProduct(id, productRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a product")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable int id) {
        var res = productService.deleteProduct(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
