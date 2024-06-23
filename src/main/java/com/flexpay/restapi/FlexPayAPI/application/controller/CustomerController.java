package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CustomerRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CustomerResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.CustomerService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer", description = "Customer API")
@RestController
@RequestMapping("/api/v1/FlexPay")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "get a customer by id")
    @GetMapping("/customer/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerById(@PathVariable("id") int id) {
        ApiResponse<CustomerResponseDTO> response = customerService.getCustomerById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "get a customer by account id")
    @GetMapping("/customer/account/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerByAccountId(@PathVariable("id") int id){
        ApiResponse<CustomerResponseDTO> response = customerService.getCustomerByAccountId(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "get all customers")
    @GetMapping("/customers")
    public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> getAllCustomers() {
        var res = customerService.getAllCustomers();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "create a new customer")
    @PostMapping("/customer")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        var res = customerService.createCustomer(customerRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing customer")
    @PutMapping("/customer/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDTO>> updateCustomer(@PathVariable int id, @RequestBody CustomerRequestDTO customerRequestDTO) {
        var res = customerService.updateCustomer(id, customerRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a customer")
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable int id) {
        var res = customerService.deleteCustomer(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
