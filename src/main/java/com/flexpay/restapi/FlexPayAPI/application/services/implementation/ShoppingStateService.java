package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.ShoppingStateRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.ShoppingStateResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IShoppingStateService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingState;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IShoppingStateRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ShoppingStateService implements IShoppingStateService {

    private final IShoppingStateRepository shoppingStateRepository;
    private final ModelMapper modelMapper;

    public ShoppingStateService(IShoppingStateRepository shoppingStateRepository, ModelMapper modelMapper) {
        this.shoppingStateRepository = shoppingStateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<ShoppingStateResponseDTO> getShoppingStateById(int id) {
        Optional<ShoppingState> shoppingStateOptional = shoppingStateRepository.findById(id);
        if (shoppingStateOptional.isPresent()){
            ShoppingState shoppingState = shoppingStateOptional.get();
            ShoppingStateResponseDTO responseDTO = modelMapper.map(shoppingState, ShoppingStateResponseDTO.class);
            return new ApiResponse<>("ShoppingState fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("ShoppingState not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<ShoppingStateResponseDTO> createShoppingState(ShoppingStateRequestDTO shoppingStateRequestDTO) {
        var shoppingState = modelMapper.map(shoppingStateRequestDTO, ShoppingState.class);
        shoppingStateRepository.save(shoppingState);
        var response = modelMapper.map(shoppingState, ShoppingStateResponseDTO.class);

        return new ApiResponse<>("ShoppingState created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<ShoppingStateResponseDTO> updateShoppingState(int id, ShoppingStateRequestDTO shoppingStateRequestDTO) {
        Optional<ShoppingState> shoppingStateOptional = shoppingStateRepository.findById(id);

        if (shoppingStateOptional.isEmpty()) {
            return new ApiResponse<>("ShoppingState not found", Estatus.ERROR, null);
        }else {
            ShoppingState shoppingState = shoppingStateOptional.get();
            modelMapper.map(shoppingStateRequestDTO, shoppingState);
            shoppingStateRepository.save(shoppingState);
            ShoppingStateResponseDTO response = modelMapper.map(shoppingState, ShoppingStateResponseDTO.class);
            return new ApiResponse<>("ShoppingState updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteShoppingState(int id) {
        Optional<ShoppingState> shoppingStateOptional = shoppingStateRepository.findById(id);

        if (shoppingStateOptional.isEmpty()) {
            return new ApiResponse<>("ShoppingState not found", Estatus.ERROR, null);
        }else {
            shoppingStateRepository.deleteById(id);
            return new ApiResponse<>("ShoppingState deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
