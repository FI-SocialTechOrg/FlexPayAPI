package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.MovementRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.MovementResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.MovementService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Movement", description = "Movement API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class MovementController {
    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @Operation(summary = "get a movement by id")
    @GetMapping("/movement/{id}")
    public ResponseEntity<ApiResponse<MovementResponseDTO>> getMovementById(@PathVariable("id") int id) {
        ApiResponse<MovementResponseDTO> response = movementService.getMovementById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new movement")
    @PostMapping("/movement")
    public ResponseEntity<ApiResponse<MovementResponseDTO>> createMovement(@RequestBody MovementRequestDTO movementRequestDTO) {
        var res = movementService.createMovement(movementRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing movement")
    @PutMapping("/movement/{id}")
    public ResponseEntity<ApiResponse<MovementResponseDTO>> updateMovement(@PathVariable int id, @RequestBody MovementRequestDTO movementRequestDTO) {
        var res = movementService.updateMovement(id, movementRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a movement")
    @DeleteMapping("/movement/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMovement(@PathVariable int id) {
        var res = movementService.deleteMovement(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
