package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayCardResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IPayCardService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.PayCard;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IPayCardRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IPaymentMethodRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PayCardService implements IPayCardService {
    private final IPayCardRepository payCardRepository;
    private final IPaymentMethodRepository paymentMethodRepository;
    private final ModelMapper modelMapper;

    public PayCardService(IPayCardRepository payCardRepository, IPaymentMethodRepository paymentMethodRepository, ModelMapper modelMapper) {
        this.payCardRepository = payCardRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<PayCardResponseDTO> getPayCardById(int id) {
        Optional<PayCard> payCardOptional = payCardRepository.findById(id);
        if (payCardOptional.isPresent()){
            PayCard payCard = payCardOptional.get();
            PayCardResponseDTO responseDTO = modelMapper.map(payCard, PayCardResponseDTO.class);
            return new ApiResponse<>("PayCard fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("PayCard not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<PayCardResponseDTO> createPayCard(PayCardRequestDTO payCardRequestDTO) {
        var payCard = modelMapper.map(payCardRequestDTO, PayCard.class);
        payCard.setPaymentMethod(paymentMethodRepository.getPaymentMethodById(payCardRequestDTO.getPaymentMethod()));
        payCardRepository.save(payCard);
        var response = modelMapper.map(payCard, PayCardResponseDTO.class);

        return new ApiResponse<>("PayCard created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<PayCardResponseDTO> updatePayCard(int id, PayCardRequestDTO payCardRequestDTO) {
        Optional<PayCard> payCardOptional = payCardRepository.findById(id);

        if (payCardOptional.isEmpty()) {
            return new ApiResponse<>("PayCard not found", Estatus.ERROR, null);
        }else {
            PayCard payCard = payCardOptional.get();
            modelMapper.map(payCardRequestDTO, payCard);
            payCard.setPaymentMethod(paymentMethodRepository.getPaymentMethodById(payCardRequestDTO.getPaymentMethod()));
            payCardRepository.save(payCard);
            PayCardResponseDTO response = modelMapper.map(payCard, PayCardResponseDTO.class);
            return new ApiResponse<>("PayCard updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deletePayCard(int id) {
        Optional<PayCard> payCardOptional = payCardRepository.findById(id);

        if (payCardOptional.isEmpty()) {
            return new ApiResponse<>("PayCard not found", Estatus.ERROR, null);
        }else {
            payCardRepository.deleteById(id);
            return new ApiResponse<>("PayCard deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
