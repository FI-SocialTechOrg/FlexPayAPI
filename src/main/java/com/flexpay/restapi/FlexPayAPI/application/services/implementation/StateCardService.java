package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.StateCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.StateCardResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IStateCardService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.StateCard;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IStateCardRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StateCardService implements IStateCardService {
    private final IStateCardRepository stateCardRepository;
    private final ModelMapper modelMapper;

    public StateCardService(IStateCardRepository stateCardRepository, ModelMapper modelMapper) {
        this.stateCardRepository = stateCardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<StateCardResponseDTO> getStateCardById(int id) {
        Optional<StateCard> stateCardOptional = stateCardRepository.findById(id);
        if (stateCardOptional.isPresent()) {
            StateCard stateCard = stateCardOptional.get();
            StateCardResponseDTO responseDTO = modelMapper.map(stateCard, StateCardResponseDTO.class);
            return new ApiResponse<>("StateCard fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("StateCard not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<StateCardResponseDTO> createStateCard(StateCardRequestDTO stateCardRequestDTO) {
        var stateCard = modelMapper.map(stateCardRequestDTO, StateCard.class);
        stateCardRepository.save(stateCard);
        var response = modelMapper.map(stateCard, StateCardResponseDTO.class);

        return new ApiResponse<>("StateCard created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<StateCardResponseDTO> updateStateCard(int id, StateCardRequestDTO stateCardRequestDTO) {
        Optional<StateCard> stateCardOptional = stateCardRepository.findById(id);

        if (stateCardOptional.isEmpty()) {
            return new ApiResponse<>("StateCard not found", Estatus.ERROR, null);
        } else {
            StateCard stateCard = stateCardOptional.get();
            modelMapper.map(stateCardRequestDTO, stateCard);
            stateCardRepository.save(stateCard);
            StateCardResponseDTO response = modelMapper.map(stateCard, StateCardResponseDTO.class);
            return new ApiResponse<>("StateCard updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteStateCard(int id) {
        Optional<StateCard> stateCardOptional = stateCardRepository.findById(id);
        if (stateCardOptional.isEmpty()) {
            return new ApiResponse<>("StateCard not found", Estatus.ERROR, null);
        } else {
            stateCardRepository.deleteById(id);
            return new ApiResponse<>("StateCard deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
