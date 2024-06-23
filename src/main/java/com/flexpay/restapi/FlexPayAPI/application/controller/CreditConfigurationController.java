package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CreditConfigurationRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CreditConfigurationResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.CreditConfigurationService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Credit Configuration", description = "Credit Configuration API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class CreditConfigurationController {
    private final CreditConfigurationService creditConfigurationService;

    public CreditConfigurationController(CreditConfigurationService creditConfigurationService) {
        this.creditConfigurationService = creditConfigurationService;
    }

    @Operation(summary = "get a credit configuration by id")
    @GetMapping("/credit-configuration/{id}")
    public ResponseEntity<ApiResponse<CreditConfigurationResponseDTO>> getCreditConfigurationById(@PathVariable("id") int id) {
        ApiResponse<CreditConfigurationResponseDTO> response = creditConfigurationService.getCreditConfigurationById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "get a credit configuration by account id")
    @GetMapping("/credit-configuration/store/account/{id}")
    public ResponseEntity<ApiResponse<CreditConfigurationResponseDTO>> getCreditConfigurationByAccountId(@PathVariable("id") int id) {
        ApiResponse<CreditConfigurationResponseDTO> response = creditConfigurationService.getCreditConfigurationByAccountId(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "get a credit configuration by store id")
    @GetMapping("/credit-configuration/store/{id}")
    public ResponseEntity<ApiResponse<CreditConfigurationResponseDTO>> getCreditConfigurationByStoreId(@PathVariable("id") int id) {
        ApiResponse<CreditConfigurationResponseDTO> response = creditConfigurationService.getCreditConfigurationByStoreId(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new credit configuration")
    @PostMapping("/credit-configuration")
    public ResponseEntity<ApiResponse<CreditConfigurationResponseDTO>> createCreditConfiguration(@RequestBody CreditConfigurationRequestDTO creditConfigurationRequestDTO) {
        var res = creditConfigurationService.createCreditConfiguration(creditConfigurationRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing credit configuration")
    @PutMapping("/credit-configuration/{id}")
    public ResponseEntity<ApiResponse<CreditConfigurationResponseDTO>> updateCreditConfiguration(@PathVariable int id, @RequestBody CreditConfigurationRequestDTO creditConfigurationRequestDTO) {
        var res = creditConfigurationService.updateCreditConfiguration(id, creditConfigurationRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a credit configuration")
    @DeleteMapping("/credit-configuration/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCreditConfiguration(@PathVariable int id) {
        var res = creditConfigurationService.deleteCreditConfiguration(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
