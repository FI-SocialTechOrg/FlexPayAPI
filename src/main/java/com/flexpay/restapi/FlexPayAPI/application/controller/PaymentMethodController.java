package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PaymentMethodRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PaymentMethodResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.PaymentMethodService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment Method", description = "Payment Method API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @Operation(summary = "get a payment method by id")
    @GetMapping("/payment-method/{id}")
    public ResponseEntity<ApiResponse<PaymentMethodResponseDTO>> getPaymentMethodById(@PathVariable("id") int id) {
        ApiResponse<PaymentMethodResponseDTO> response = paymentMethodService.getPaymentMethodById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new payment method")
    @PostMapping("/payment-method")
    public ResponseEntity<ApiResponse<PaymentMethodResponseDTO>> createPaymentMethod(@RequestBody PaymentMethodRequestDTO paymentMethodRequestDTO) {
        var res = paymentMethodService.createPaymentMethod(paymentMethodRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing payment method")
    @PutMapping("/payment-method/{id}")
    public ResponseEntity<ApiResponse<PaymentMethodResponseDTO>> updatePaymentMethod(@PathVariable int id, @RequestBody PaymentMethodRequestDTO paymentMethodRequestDTO) {
        var res = paymentMethodService.updatePaymentMethod(id, paymentMethodRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a payment method")
    @DeleteMapping("/payment-method/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePaymentMethod(@PathVariable int id) {
        var res = paymentMethodService.deletePaymentMethod(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
