package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingStockResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.ShoppingStockService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Shopping Stock", description = "Shopping Stock API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class ShoppingStockController {
    private final ShoppingStockService shoppingStockService;

    public ShoppingStockController(ShoppingStockService shoppingStockService) {
        this.shoppingStockService = shoppingStockService;
    }

    @Operation(summary = "get a shopping stock by id")
    @GetMapping("/shopping-stock/{id}")
    public ResponseEntity<ApiResponse<ShoppingStockResponseDTO>> getShoppingStockById(@PathVariable("id") int id) {
        ApiResponse<ShoppingStockResponseDTO> response = shoppingStockService.getShoppingStockById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }



    @Operation(summary = "get all shopping stocks")
    @GetMapping("/shopping-stocks")
    public ResponseEntity<ApiResponse<List<ShoppingStockResponseDTO>>> getAllShoppingStocks() {
        var res = shoppingStockService.getAllShoppingStocks();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @Operation(summary = "create a new shopping stock")
    @PostMapping("/shopping-stock")
    public ResponseEntity<ApiResponse<ShoppingStockResponseDTO>> createShoppingStock(@RequestBody ShoppingStockRequestDTO shoppingStockRequestDTO) {
        var res = shoppingStockService.createShoppingStock(shoppingStockRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing shopping stock")
    @PutMapping("/shopping-stock/{id}")
    public ResponseEntity<ApiResponse<ShoppingStockResponseDTO>> updateShoppingStock(@PathVariable int id, @RequestBody ShoppingStockRequestDTO shoppingStockRequestDTO) {
        var res = shoppingStockService.updateShoppingStock(id, shoppingStockRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a shopping stock")
    @DeleteMapping("/shopping-stock/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteShoppingStock(@PathVariable int id) {
        var res = shoppingStockService.deleteShoppingStock(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
