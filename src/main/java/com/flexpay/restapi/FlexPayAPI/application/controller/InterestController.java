package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.InterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.InterestResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.InterestService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Interest", description = "Interest API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class InterestController {
    private final InterestService interestService;

    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @Operation(summary = "get an interest by id")
    @GetMapping("/interest/{id}")
    public ResponseEntity<ApiResponse<InterestResponseDTO>> getInterestById(@PathVariable("id") int id) {
        ApiResponse<InterestResponseDTO> response = interestService.getInterestById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new interest")
    @PostMapping("/interest")
    public ResponseEntity<ApiResponse<InterestResponseDTO>> createInterest(@RequestBody InterestRequestDTO interestRequestDTO) {
        var res = interestService.createInterest(interestRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing interest")
    @PutMapping("/interest/{id}")
    public ResponseEntity<ApiResponse<InterestResponseDTO>> updateInterest(@PathVariable int id, @RequestBody InterestRequestDTO interestRequestDTO) {
        var res = interestService.updateInterest(id, interestRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete an interest")
    @DeleteMapping("/interest/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInterest(@PathVariable int id) {
        var res = interestService.deleteInterest(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
