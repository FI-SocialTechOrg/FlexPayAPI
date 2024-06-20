package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StoreRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StoreResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StoreWithoutProductStockResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.StoreService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Store", description = "Store API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @Operation(summary = "get a store by id")
    @GetMapping("/store/{id}")
    public ResponseEntity<ApiResponse<StoreResponseDTO>> getStoreById(@PathVariable("id") int id) {
        ApiResponse<StoreResponseDTO> response = storeService.getStoreById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "get all stores")
    @GetMapping("/store")
    public ResponseEntity<ApiResponse<List<StoreResponseDTO>>> getAllStores() {
        var res = storeService.getAllStores();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new store")
    @PostMapping("/store")
    public ResponseEntity<ApiResponse<StoreResponseDTO>> createStore(@RequestBody StoreRequestDTO storeRequestDTO) {
        var res = storeService.createStore(storeRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing store")
    @PutMapping("/store/{id}")
    public ResponseEntity<ApiResponse<StoreResponseDTO>> updateStore(@PathVariable int id, @RequestBody StoreRequestDTO storeRequestDTO) {
        var res = storeService.updateStore(id, storeRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a store")
    @DeleteMapping("/store/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStore(@PathVariable int id) {
        var res = storeService.deleteStore(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
