package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingCartRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingCartResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.ShoppingCartService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Shopping Cart", description = "Shopping Cart API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class ShoppingCartController {
    final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Operation(summary = "get a shopping cart by id")
    @GetMapping("/shopping-cart/{id}")
    public ResponseEntity<ApiResponse<ShoppingCartResponseDTO>> getShoppingCartById(@PathVariable("id") int id) {
        ApiResponse<ShoppingCartResponseDTO> response = shoppingCartService.getShoppingCartById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "get a shopping cart by account id")
    @GetMapping("/shopping-cart/customer/account/{id}")
    public ResponseEntity<ApiResponse<ShoppingCartResponseDTO>> getShoppingCartByAccountId(@PathVariable("id") int id) {
        ApiResponse<ShoppingCartResponseDTO> response = shoppingCartService.getShoppingCartByAccountId(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new shopping cart")
    @PostMapping("/shopping-cart")
    public ResponseEntity<ApiResponse<ShoppingCartResponseDTO>> createShoppingCart(@RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
        var res = shoppingCartService.createShoppingCart(shoppingCartRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing shopping cart")
    @PutMapping("/shopping-cart/{id}")
    public ResponseEntity<ApiResponse<ShoppingCartResponseDTO>> updateShoppingCart(@PathVariable int id, @RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO) {
        var res = shoppingCartService.updateShoppingCart(id, shoppingCartRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a shopping cart")
    @DeleteMapping("/shopping-cart/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteShoppingCart(@PathVariable int id) {
        var res = shoppingCartService.deleteShoppingCart(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
