package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CapitalizationPeriodRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CapitalizationPeriodResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.CapitalizationPeriodService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Capitalization Period", description = "Capitalization Period API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class CapitalizationPeriodController {
    private final CapitalizationPeriodService capitalizationPeriodService;

    public CapitalizationPeriodController(CapitalizationPeriodService capitalizationPeriodService) {
        this.capitalizationPeriodService = capitalizationPeriodService;
    }

    @Operation(summary = "get a capitalization period by id")
    @GetMapping("/capitalization-period/{id}")
    public ResponseEntity<ApiResponse<CapitalizationPeriodResponseDTO>> getCapitalizationPeriodById(@PathVariable("id") int id) {
        ApiResponse<CapitalizationPeriodResponseDTO> response = capitalizationPeriodService.getCapitalizationPeriodById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new capitalization period")
    @PostMapping("/capitalization-period")
    public ResponseEntity<ApiResponse<CapitalizationPeriodResponseDTO>> createCapitalizationPeriod(@RequestBody CapitalizationPeriodRequestDTO capitalizationPeriodRequestDTO) {
        var res = capitalizationPeriodService.createCapitalizationPeriod(capitalizationPeriodRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing capitalization period")
    @PutMapping("/capitalization-period/{id}")
    public ResponseEntity<ApiResponse<CapitalizationPeriodResponseDTO>> updateCapitalizationPeriod(@PathVariable int id, @RequestBody CapitalizationPeriodRequestDTO capitalizationPeriodRequestDTO) {
        var res = capitalizationPeriodService.updateCapitalizationPeriod(id, capitalizationPeriodRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a capitalization period")
    @DeleteMapping("/capitalization-period/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCapitalizationPeriod(@PathVariable int id) {
        var res = capitalizationPeriodService.deleteCapitalizationPeriod(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
