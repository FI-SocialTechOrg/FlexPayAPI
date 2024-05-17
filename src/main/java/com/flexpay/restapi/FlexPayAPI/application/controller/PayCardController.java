package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayCardResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.PayCardService;
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
public class PayCardController {
    private final PayCardService payCardService;

    public PayCardController(PayCardService payCardService) {
        this.payCardService = payCardService;
    }

    @Operation(summary = "get a pay card by id")
    @GetMapping("/pay-card/{id}")
    public ResponseEntity<ApiResponse<PayCardResponseDTO>> getPayCardById(@PathVariable("id") int id) {
        ApiResponse<PayCardResponseDTO> response = payCardService.getPayCardById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new pay card")
    @PostMapping("/pay-card")
    public ResponseEntity<ApiResponse<PayCardResponseDTO>> createPayCard(@RequestBody PayCardRequestDTO payCardRequestDTO) {
        var res = payCardService.createPayCard(payCardRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing pay card")
    @PutMapping("/pay-card/{id}")
    public ResponseEntity<ApiResponse<PayCardResponseDTO>> updatePayCard(@PathVariable int id, @RequestBody PayCardRequestDTO payCardRequestDTO) {
        var res = payCardService.updatePayCard(id, payCardRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a pay card")
    @DeleteMapping("/pay-card/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayCard(@PathVariable int id) {
        var res = payCardService.deletePayCard(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
