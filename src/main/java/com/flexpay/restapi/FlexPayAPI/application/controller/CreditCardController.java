package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CreditCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CreditCardResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.CreditCardService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Credit Card", description = "Credit Card API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Operation(summary = "get a credit card by id")
    @GetMapping("/credit-card/{id}")
    public ResponseEntity<ApiResponse<CreditCardResponseDTO>> getCreditCardById(@PathVariable("id") int id) {
        ApiResponse<CreditCardResponseDTO> response = creditCardService.getCreditCardById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new credit card")
    @PostMapping("/credit-card")
    public ResponseEntity<ApiResponse<CreditCardResponseDTO>> createCreditCard(@RequestBody CreditCardRequestDTO creditCardRequestDTO) {
        var res = creditCardService.createCreditCard(creditCardRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing credit card")
    @PutMapping("/credit-card/{id}")
    public ResponseEntity<ApiResponse<CreditCardResponseDTO>> updateCreditCard(@PathVariable int id, @RequestBody CreditCardRequestDTO creditCardRequestDTO) {
        var res = creditCardService.updateCreditCard(id, creditCardRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a credit card")
    @DeleteMapping("/credit-card/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCreditCard(@PathVariable int id) {
        var res = creditCardService.deleteCreditCard(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
