package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StateStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StateStockResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.StateStockService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "State Stock", description = "State Stock API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class StateStockController {
    private final StateStockService stateStockService;

    public StateStockController(StateStockService stateStockService) {
        this.stateStockService = stateStockService;
    }

    @Operation(summary = "get a state stock by id")
    @GetMapping("/state-stock/{id}")
    public ResponseEntity<ApiResponse<StateStockResponseDTO>> getStateStockById(@PathVariable("id") int id) {
        ApiResponse<StateStockResponseDTO> response = stateStockService.getStateStocksbyId(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new state stock")
    @PostMapping("/state-stock")
    public ResponseEntity<ApiResponse<StateStockResponseDTO>> createStateStock(@RequestBody StateStockRequestDTO stateStockRequestDTO) {
        var res = stateStockService.createStateStock(stateStockRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing state stock")
    @PutMapping("/state-stock/{id}")
    public ResponseEntity<ApiResponse<StateStockResponseDTO>> updateStateStock(@PathVariable int id, @RequestBody StateStockRequestDTO stateStockRequestDTO) {
        var res = stateStockService.updateStateStock(id, stateStockRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a state stock")
    @DeleteMapping("/state-stock/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStateStock(@PathVariable int id) {
        var res = stateStockService.deleteStateStock(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
