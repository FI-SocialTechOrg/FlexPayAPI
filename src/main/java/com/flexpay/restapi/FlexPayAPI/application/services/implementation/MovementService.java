package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.MovementRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.MovementResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IMovementService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Movement;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.ICreditCardRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IInterestRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IMovementRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovementService implements IMovementService {

    private final IMovementRepository movementRepository;
    private final ICreditCardRepository creditCardRepository;
    private final IInterestRepository interestRepository;
    private final ModelMapper modelMapper;

    public MovementService(IMovementRepository movementRepository, ICreditCardRepository creditCardRepository, IInterestRepository interestRepository, ModelMapper modelMapper) {
        this.movementRepository = movementRepository;
        this.creditCardRepository = creditCardRepository;
        this.interestRepository = interestRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<MovementResponseDTO> getMovementById(int id) {
        Optional<Movement> movementOptional = movementRepository.findById(id);
        if (movementOptional.isPresent()){
            Movement movement = movementOptional.get();
            MovementResponseDTO responseDTO = modelMapper.map(movement, MovementResponseDTO.class);
            return new ApiResponse<>("Movement fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Movement not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<MovementResponseDTO> createMovement(MovementRequestDTO movementRequestDTO) {
        var movement = modelMapper.map(movementRequestDTO, Movement.class);
        movement.setCreditCard(creditCardRepository.getCreditCardById(movementRequestDTO.getCreditCard()));
        movement.setInterest(interestRepository.getInterestById(movementRequestDTO.getInterest()));
        movementRepository.save(movement);
        var response = modelMapper.map(movement, MovementResponseDTO.class);

        return new ApiResponse<>("Movement created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<MovementResponseDTO> updateMovement(int id, MovementRequestDTO movementRequestDTO) {
        Optional<Movement> movementOptional = movementRepository.findById(id);

        if (movementOptional.isEmpty()) {
            return new ApiResponse<>("Movement not found", Estatus.ERROR, null);
        }else {
            Movement movement = movementOptional.get();
            modelMapper.map(movementRequestDTO, movement);
            movement.setCreditCard(creditCardRepository.getCreditCardById(movementRequestDTO.getCreditCard()));
            movement.setInterest(interestRepository.getInterestById(movementRequestDTO.getInterest()));
            movementRepository.save(movement);
            MovementResponseDTO response = modelMapper.map(movement, MovementResponseDTO.class);
            return new ApiResponse<>("Movement updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteMovement(int id) {
        Optional<Movement> movementOptional = movementRepository.findById(id);

        if (movementOptional.isEmpty()) {
            return new ApiResponse<>("Movement not found", Estatus.ERROR, null);
        }else {
            movementRepository.deleteById(id);
            return new ApiResponse<>("Movement deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
