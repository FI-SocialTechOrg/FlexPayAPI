package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StateCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StateCardResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.StateCardService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "State Card", description = "State Card API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class StateCardController {
    private final StateCardService stateCardService;

    public StateCardController(StateCardService stateCardService) {
        this.stateCardService = stateCardService;
    }

    @Operation(summary = "get a state card by id")
    @GetMapping("/state-card/{id}")
    public ResponseEntity<ApiResponse<StateCardResponseDTO>> getStateCardById(@PathVariable("id") int id) {
        ApiResponse<StateCardResponseDTO> response = stateCardService.getStateCardById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new state card")
    @PostMapping("/state-card")
    public ResponseEntity<ApiResponse<StateCardResponseDTO>> createStateCard(@RequestBody StateCardRequestDTO stateCardRequestDTO) {
        var res = stateCardService.createStateCard(stateCardRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing state card")
    @PutMapping("/state-card/{id}")
    public ResponseEntity<ApiResponse<StateCardResponseDTO>> updateStateCard(@PathVariable int id, @RequestBody StateCardRequestDTO stateCardRequestDTO) {
        var res = stateCardService.updateStateCard(id, stateCardRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a state card")
    @DeleteMapping("/state-card/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStateCard(@PathVariable int id) {
        var res = stateCardService.deleteStateCard(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
