package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CustomerRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CustomerResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.ICustomerService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Customer;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IAccountRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.ICustomerRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    private final IAccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public CustomerService(ICustomerRepository customerRepository, IAccountRepository accountRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<CustomerResponseDTO> getCustomerById(int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            CustomerResponseDTO responseDTO = modelMapper.map(customer, CustomerResponseDTO.class);
            return new ApiResponse<>("Customer fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Customer not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<CustomerResponseDTO>> getAllCustomers() {
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOList = customerList.stream()
                .map(entity -> modelMapper.map(entity, CustomerResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All customers fetched successfully", Estatus.SUCCESS, customerResponseDTOList);
    }

    @Override
    public ApiResponse<CustomerResponseDTO> createCustomer(CustomerRequestDTO customerRequestDTO) {
        var customer = modelMapper.map(customerRequestDTO, Customer.class);
        customer.setAccount(accountRepository.getAccountById(customerRequestDTO.getAccount()));
        customerRepository.save(customer);
        var response = modelMapper.map(customer, CustomerResponseDTO.class);
        return new ApiResponse<>("Customer created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<CustomerResponseDTO> updateCustomer(int id, CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()) {
            return new ApiResponse<>("Store not found", Estatus.ERROR, null);
        }else {
            Customer customer = customerOptional.get();
            modelMapper.map(customerRequestDTO, customer);
            customer.setAccount(accountRepository.getAccountById(customerRequestDTO.getAccount()));
            customerRepository.save(customer);
            CustomerResponseDTO response = modelMapper.map(customer, CustomerResponseDTO.class);
            return new ApiResponse<>("Store updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteCustomer(int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()) {
            return new ApiResponse<>("Customer not found", Estatus.ERROR, null);
        }else {
            customerRepository.deleteById(id);
            return new ApiResponse<>("Customer deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
