package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CustomerRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CustomerResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface ICustomerService {
    ApiResponse<CustomerResponseDTO> getCustomerById(int id);
    ApiResponse<List<CustomerResponseDTO>> getAllCustomers();
    ApiResponse<CustomerResponseDTO> createCustomer(CustomerRequestDTO customerRequestDTO);
    ApiResponse<CustomerResponseDTO> updateCustomer(int id, CustomerRequestDTO customerRequestDTO);
    ApiResponse<Void> deleteCustomer(int id);
}
