package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PaymentMethodRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PaymentMethodResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IPaymentMethodService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.PaymentMethod;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IPayCardRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IPaymentMethodRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService implements IPaymentMethodService {
    private final IPaymentMethodRepository paymentMethodRepository;
    private final IPayCardRepository payCardRepository;
    private final ModelMapper modelMapper;

    public PaymentMethodService(IPaymentMethodRepository paymentMethodRepository, IPayCardRepository payCardRepository, ModelMapper modelMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.payCardRepository = payCardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<PaymentMethodResponseDTO> getPaymentMethodById(int id) {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(id);
        if (paymentMethodOptional.isPresent()){
            PaymentMethod paymentMethod = paymentMethodOptional.get();
            PaymentMethodResponseDTO responseDTO = modelMapper.map(paymentMethod, PaymentMethodResponseDTO.class);
            return new ApiResponse<>("PaymentMethod fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("PaymentMethod not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<PaymentMethodResponseDTO> createPaymentMethod(PaymentMethodRequestDTO paymentMethodRequestDTO) {
        var paymentMethod = modelMapper.map(paymentMethodRequestDTO, PaymentMethod.class);
        paymentMethod.setPayCard(payCardRepository.getPayCardById(paymentMethodRequestDTO.getPayCard()));
        paymentMethodRepository.save(paymentMethod);
        var response = modelMapper.map(paymentMethod, PaymentMethodResponseDTO.class);

        return new ApiResponse<>("PaymentMethod created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<PaymentMethodResponseDTO> updatePaymentMethod(int id, PaymentMethodRequestDTO paymentMethodRequestDTO) {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(id);

        if (paymentMethodOptional.isEmpty()) {
            return new ApiResponse<>("PaymentMethod not found", Estatus.ERROR, null);
        }else {
            PaymentMethod paymentMethod = paymentMethodOptional.get();
            modelMapper.map(paymentMethodRequestDTO, paymentMethod);
            paymentMethod.setPayCard(payCardRepository.getPayCardById(paymentMethodRequestDTO.getPayCard()));
            paymentMethodRepository.save(paymentMethod);
            PaymentMethodResponseDTO response = modelMapper.map(paymentMethod, PaymentMethodResponseDTO.class);
            return new ApiResponse<>("PaymentMethod updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deletePaymentMethod(int id) {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(id);

        if (paymentMethodOptional.isEmpty()) {
            return new ApiResponse<>("PaymentMethod not found", Estatus.ERROR, null);
        }else {
            paymentMethodRepository.deleteById(id);
            return new ApiResponse<>("PaymentMethod deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
