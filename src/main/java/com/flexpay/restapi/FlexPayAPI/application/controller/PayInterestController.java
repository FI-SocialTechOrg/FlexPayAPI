package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayInterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayInterestResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.PayInterestService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pay Interest", description = "Pay Interest API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class PayInterestController {
    private final PayInterestService payInterestService;

    public PayInterestController(PayInterestService payInterestService) {
        this.payInterestService = payInterestService;
    }

    @Operation(summary = "get an interest by id")
    @GetMapping("/pay-interest/{id}")
    public ResponseEntity<ApiResponse<PayInterestResponseDTO>> getPayInterestById(@PathVariable("id") int id) {
        ApiResponse<PayInterestResponseDTO> response = payInterestService.getPayInterestById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new interest")
    @PostMapping("/pay-interest")
    public ResponseEntity<ApiResponse<PayInterestResponseDTO>> createPayInterest(@RequestBody PayInterestRequestDTO payInterestRequestDTO) {
        var res = payInterestService.createPayInterest(payInterestRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing interest")
    @PutMapping("/pay-interest/{id}")
    public ResponseEntity<ApiResponse<PayInterestResponseDTO>> updatePayInterest(@PathVariable int id, @RequestBody PayInterestRequestDTO payInterestRequestDTO) {
        var res = payInterestService.updatePayInterest(id, payInterestRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete an interest")
    @DeleteMapping("/pay-interest/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayInterest(@PathVariable int id) {
        var res = payInterestService.deletePayInterest(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
