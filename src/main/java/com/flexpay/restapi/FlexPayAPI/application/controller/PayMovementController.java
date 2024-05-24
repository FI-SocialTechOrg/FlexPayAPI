package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayMovementRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayMovementResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.PayMovementService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pay Card", description = "Pay Card API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class PayMovementController {
    private final PayMovementService payMovementService;

    public PayMovementController(PayMovementService payMovementService) {
        this.payMovementService = payMovementService;
    }

    @Operation(summary = "get a pay card by id")
    @GetMapping("/pay-card/{id}")
    public ResponseEntity<ApiResponse<PayMovementResponseDTO>> getPayMovementById(@PathVariable("id") int id) {
        ApiResponse<PayMovementResponseDTO> response = payMovementService.getPayMovementById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new pay card")
    @PostMapping("/pay-card")
    public ResponseEntity<ApiResponse<PayMovementResponseDTO>> createPayMovement(@RequestBody PayMovementRequestDTO payMovementRequestDTO) {
        var res = payMovementService.createPayMovement(payMovementRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing pay card")
    @PutMapping("/pay-card/{id}")
    public ResponseEntity<ApiResponse<PayMovementResponseDTO>> updatePayMovement(@PathVariable int id, @RequestBody PayMovementRequestDTO payMovementRequestDTO) {
        var res = payMovementService.updatePayMovement(id, payMovementRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a pay card")
    @DeleteMapping("/pay-card/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayMovement(@PathVariable int id) {
        var res = payMovementService.deletePayMovement(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
