package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.TypeInterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.TypeInterestResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.TypeInterestService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Type Interest", description = "Type Interest API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class TypeInterestController {
    private final TypeInterestService typeInterestService;

    public TypeInterestController(TypeInterestService typeInterestService) {
        this.typeInterestService = typeInterestService;
    }

    @Operation(summary = "get a type interest by id")
    @GetMapping("/type-interest/{id}")
    public ResponseEntity<ApiResponse<TypeInterestResponseDTO>> getTypeInterestById(@PathVariable("id") int id) {
        ApiResponse<TypeInterestResponseDTO> response = typeInterestService.getTypeInterestById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "create a new type interest")
    @PostMapping("/type-interest")
    public ResponseEntity<ApiResponse<TypeInterestResponseDTO>> createTypeInterest(@RequestBody TypeInterestRequestDTO typeInterestRequestDTO) {
        var res = typeInterestService.createTypeInterest(typeInterestRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing type interest")
    @PutMapping("/type-interest/{id}")
    public ResponseEntity<ApiResponse<TypeInterestResponseDTO>> updateTypeInterest(@PathVariable int id, @RequestBody TypeInterestRequestDTO typeInterestRequestDTO) {
        var res = typeInterestService.updateTypeInterest(id, typeInterestRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a type interest")
    @DeleteMapping("/type-interest/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTypeInterest(@PathVariable int id) {
        var res = typeInterestService.deleteTypeInterest(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
