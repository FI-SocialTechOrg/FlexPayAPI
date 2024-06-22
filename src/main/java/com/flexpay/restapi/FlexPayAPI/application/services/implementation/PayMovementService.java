package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayMovementRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayMovementResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IPayMovementService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.PayMovement;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IMovementRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IPayMovementRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IPaymentMethodRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PayMovementService implements IPayMovementService {
    private final IPayMovementRepository payMovementRepository;
    private final IMovementRepository movementRepository;
    private final IPaymentMethodRepository paymentMethodRepository;
    private final ModelMapper modelMapper;

    public PayMovementService(IPayMovementRepository payMovementRepository, IMovementRepository movementRepository, IPaymentMethodRepository paymentMethodRepository, ModelMapper modelMapper) {
        this.payMovementRepository = payMovementRepository;
        this.movementRepository = movementRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<PayMovementResponseDTO> getPayMovementById(int id) {
        Optional<PayMovement> payMovementOptional = payMovementRepository.findById(id);
        if (payMovementOptional.isPresent()){
            PayMovement payMovement = payMovementOptional.get();
            PayMovementResponseDTO responseDTO = modelMapper.map(payMovement, PayMovementResponseDTO.class);
            return new ApiResponse<>("PayMovement fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("PayMovement not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<PayMovementResponseDTO> createPayMovement(PayMovementRequestDTO payMovementRequestDTO) {
        var payMovement = modelMapper.map(payMovementRequestDTO, PayMovement.class);
        payMovement.setMovement(movementRepository.getMovementById(payMovementRequestDTO.getMovementId()));
        payMovement.setPaymentMethod(paymentMethodRepository.getPaymentMethodById(payMovementRequestDTO.getPaymentMethodId()));
        payMovementRepository.save(payMovement);
        var response = modelMapper.map(payMovement, PayMovementResponseDTO.class);

        return new ApiResponse<>("PayMovement created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<PayMovementResponseDTO> updatePayMovement(int id, PayMovementRequestDTO payMovementRequestDTO) {
        Optional<PayMovement> payMovementOptional = payMovementRepository.findById(id);

        if (payMovementOptional.isEmpty()) {
            return new ApiResponse<>("PayMovement not found", Estatus.ERROR, null);
        }else {
            PayMovement payMovement = payMovementOptional.get();
            modelMapper.map(payMovementRequestDTO, payMovement);
            payMovement.setMovement(movementRepository.getMovementById(payMovementRequestDTO.getMovementId()));
            payMovement.setPaymentMethod(paymentMethodRepository.getPaymentMethodById(payMovementRequestDTO.getPaymentMethodId()));
            payMovementRepository.save(payMovement);
            PayMovementResponseDTO response = modelMapper.map(payMovement, PayMovementResponseDTO.class);
            return new ApiResponse<>("PayMovement updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deletePayMovement(int id) {
        Optional<PayMovement> payMovementOptional = payMovementRepository.findById(id);

        if (payMovementOptional.isEmpty()) {
            return new ApiResponse<>("PayMovement not found", Estatus.ERROR, null);
        }else {
            payMovementRepository.deleteById(id);
            return new ApiResponse<>("PayMovement deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
