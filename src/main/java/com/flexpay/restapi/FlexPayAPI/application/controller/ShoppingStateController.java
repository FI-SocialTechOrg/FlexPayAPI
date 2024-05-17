package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingStateRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingStateResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.ShoppingStateService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Shopping State", description = "Shopping State API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class ShoppingStateController {
    private final ShoppingStateService shoppingStateService;

    public ShoppingStateController(ShoppingStateService shoppingStateService) {
        this.shoppingStateService = shoppingStateService;
    }

    @Operation(summary = "get a shopping state by id")
    @GetMapping("/shoppingState/{id}")
    public ResponseEntity<ApiResponse<ShoppingStateResponseDTO>> getShoppingStateById(@PathVariable("id") int id) {
        ApiResponse<ShoppingStateResponseDTO> response = shoppingStateService.getShoppingStateById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new shopping state")
    @PostMapping("/shoppingState")
    public ResponseEntity<ApiResponse<ShoppingStateResponseDTO>> createShoppingState(@RequestBody ShoppingStateRequestDTO shoppingStateRequestDTO) {
        var res = shoppingStateService.createShoppingState(shoppingStateRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing shopping state")
    @PutMapping("/shoppingState/{id}")
    public ResponseEntity<ApiResponse<ShoppingStateResponseDTO>> updateShoppingState(@PathVariable int id, @RequestBody ShoppingStateRequestDTO shoppingStateRequestDTO) {
        var res = shoppingStateService.updateShoppingState(id, shoppingStateRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a shopping state")
    @DeleteMapping("/shoppingState/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteShoppingState(@PathVariable int id) {
        var res = shoppingStateService.deleteShoppingState(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
