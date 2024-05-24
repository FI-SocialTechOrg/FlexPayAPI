package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayInterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayInterestResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IPayInterestService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.PayInterest;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IPayInterestRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayInterestService implements IPayInterestService {
    private final IPayInterestRepository payInterestRepository;
    private final ModelMapper modelMapper;

    public PayInterestService(IPayInterestRepository payInterestRepository, ModelMapper modelMapper) {
        this.payInterestRepository = payInterestRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<PayInterestResponseDTO> getPayInterestById(int id) {
        Optional<PayInterest> payInterestOptional = payInterestRepository.findById(id);
        if (payInterestOptional.isPresent()){
            PayInterest payInterest = payInterestOptional.get();
            PayInterestResponseDTO responseDTO = modelMapper.map(payInterest, PayInterestResponseDTO.class);

            return new ApiResponse<>("Interest fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Interest not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<PayInterestResponseDTO> createPayInterest(PayInterestRequestDTO payInterestRequestDTO) {
        var payInterest = modelMapper.map(payInterestRequestDTO, PayInterest.class);
        payInterestRepository.save(payInterest);
        var response = modelMapper.map(payInterest, PayInterestResponseDTO.class);

        return new ApiResponse<>("Interest created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<PayInterestResponseDTO> updatePayInterest(int id, PayInterestRequestDTO payInterestRequestDTO) {
        Optional<PayInterest> payInterestOptional = payInterestRepository.findById(id);
        if (payInterestOptional.isPresent()){
            PayInterest payInterest = payInterestOptional.get();
            modelMapper.map(payInterestRequestDTO, payInterest);
            payInterestRepository.save(payInterest);
            PayInterestResponseDTO responseDTO = modelMapper.map(payInterest, PayInterestResponseDTO.class);

            return new ApiResponse<>("Interest updated successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Interest not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<Void> deletePayInterest(int id) {
        Optional<PayInterest> payInterestOptional = payInterestRepository.findById(id);
        if (payInterestOptional.isEmpty()) {
            return new ApiResponse<>("PayCard not found", Estatus.ERROR, null);
        }else {
            payInterestRepository.deleteById(id);
            return new ApiResponse<>("PayCard deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
