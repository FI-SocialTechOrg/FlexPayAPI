package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StateStockRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StateStockResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IStateStockService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.StateStock;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IStateStockRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateStockService implements IStateStockService {
    private final IStateStockRepository stateStockRepository;
    private final ModelMapper modelMapper;

    public StateStockService(IStateStockRepository stateStockRepository, ModelMapper modelMapper) {
        this.stateStockRepository = stateStockRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<StateStockResponseDTO> getStateStocksbyId(int id) {
        Optional<StateStock> stateStockOptional = stateStockRepository.findById(id);
        if (stateStockOptional.isPresent()) {
            StateStock stateStock = stateStockOptional.get();
            StateStockResponseDTO responseDTO = modelMapper.map(stateStock, StateStockResponseDTO.class);
            return new ApiResponse<>("StateStock fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("StateStock not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<StateStockResponseDTO> createStateStock(StateStockRequestDTO stateStockRequestDTO) {
        var stateStock = modelMapper.map(stateStockRequestDTO, StateStock.class);
        stateStockRepository.save(stateStock);
        var response = modelMapper.map(stateStock, StateStockResponseDTO.class);
        return new ApiResponse<>("StateStock created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<StateStockResponseDTO> updateStateStock(int id, StateStockRequestDTO stateStockRequestDTO) {
        Optional<StateStock> stateStockOptional = stateStockRepository.findById(id);
        if (stateStockOptional.isPresent()) {
            StateStock stateStock = stateStockOptional.get();
            modelMapper.map(stateStockRequestDTO, stateStock);
            stateStockRepository.save(stateStock);
            var response = modelMapper.map(stateStock, StateStockResponseDTO.class);
            return new ApiResponse<>("StateStock updated successfully", Estatus.SUCCESS, response);
        } else {
            return new ApiResponse<>("StateStock not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<Void> deleteStateStock(int id) {
        Optional<StateStock> stateStockOptional = stateStockRepository.findById(id);
        if (stateStockOptional.isEmpty()) {
            return new ApiResponse<>("Store not found", Estatus.ERROR, null);
        }else {
            stateStockRepository.deleteById(id);
            return new ApiResponse<>("Store deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
