package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingCartRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.AccountResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CustomerResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingCartResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IShoppingCartService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Account;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Customer;
import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingCart;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.ICustomerRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IShoppingCartRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IShoppingStateRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService {
    private final IShoppingCartRepository shoppingCartRepository;
    private final ICustomerRepository customerRepository;
    private final IShoppingStateRepository shoppingStateRepository;
    private final ModelMapper modelMapper;

    public ShoppingCartService(IShoppingCartRepository shoppingCartRepository, ICustomerRepository customerRepository, IShoppingStateRepository shoppingStateRepository, ModelMapper modelMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
        this.shoppingStateRepository = shoppingStateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<ShoppingCartResponseDTO> getShoppingCartById(int id) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);
        if (shoppingCartOptional.isPresent()){
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            ShoppingCartResponseDTO responseDTO = modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
            return new ApiResponse<>("ShoppingCart fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("ShoppingCart not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<ShoppingCartResponseDTO> getShoppingCartByAccountId(int accountId) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.getShoppingCartByCustomer_Account_Id(accountId);
        if (shoppingCartOptional.isPresent()){
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            ShoppingCartResponseDTO responseDTO = modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
            return new ApiResponse<>("ShoppingCart fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("ShoppingCart not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<ShoppingCartResponseDTO> createShoppingCart(ShoppingCartRequestDTO shoppingCartRequestDTO) {
        var shoppingCart = modelMapper.map(shoppingCartRequestDTO, ShoppingCart.class);
        shoppingCart.setCustomer(customerRepository.getCustomerById(shoppingCartRequestDTO.getCustomer()));
        shoppingCart.setShoppingState(shoppingStateRepository.getShoppingStateById(shoppingCartRequestDTO.getShoppingState()));
        shoppingCartRepository.save(shoppingCart);
        var response = modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);

        return new ApiResponse<>("ShoppingCart created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<ShoppingCartResponseDTO> updateShoppingCart(int id, ShoppingCartRequestDTO shoppingCartRequestDTO) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);

        if (shoppingCartOptional.isEmpty()) {
            return new ApiResponse<>("ShoppingCart not found", Estatus.ERROR, null);
        }else {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            modelMapper.map(shoppingCartRequestDTO, shoppingCart);
            shoppingCart.setCustomer(customerRepository.getCustomerById(shoppingCartRequestDTO.getCustomer()));
            shoppingCart.setShoppingState(shoppingStateRepository.getShoppingStateById(shoppingCartRequestDTO.getShoppingState()));
            shoppingCartRepository.save(shoppingCart);
            ShoppingCartResponseDTO response = modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class);
            return new ApiResponse<>("ShoppingCart updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteShoppingCart(int id) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);

        if (shoppingCartOptional.isEmpty()) {
            return new ApiResponse<>("ShoppingCart not found", Estatus.ERROR, null);
        }else {
            shoppingCartRepository.deleteById(id);
            return new ApiResponse<>("ShoppingCart deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
