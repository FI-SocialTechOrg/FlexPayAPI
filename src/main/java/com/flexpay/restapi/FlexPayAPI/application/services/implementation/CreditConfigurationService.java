package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CreditConfigurationRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CreditConfigurationResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.ICreditConfigurationService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.CreditConfiguration;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.ICreditConfigurationRepository;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IStoreRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditConfigurationService implements ICreditConfigurationService {
    private final ICreditConfigurationRepository creditConfigurationRepository;
    private final IStoreRepository storeRepository;
    private final ModelMapper modelMapper;

    public CreditConfigurationService(ICreditConfigurationRepository creditConfigurationRepository, IStoreRepository storeRepository, ModelMapper modelMapper) {
        this.creditConfigurationRepository = creditConfigurationRepository;
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<CreditConfigurationResponseDTO> getCreditConfigurationById(int id) {
        Optional<CreditConfiguration> creditConfigurationOptional = creditConfigurationRepository.findById(id);
        if (creditConfigurationOptional.isPresent()){
            CreditConfiguration creditConfiguration = creditConfigurationOptional.get();
            CreditConfigurationResponseDTO responseDTO = modelMapper.map(creditConfiguration, CreditConfigurationResponseDTO.class);
            return new ApiResponse<>("Credit Configuration fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Credit Configuration not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<CreditConfigurationResponseDTO> createCreditConfiguration(CreditConfigurationRequestDTO creditConfigurationRequestDTO) {
        var creditConfiguration = modelMapper.map(creditConfigurationRequestDTO, CreditConfiguration.class);
        creditConfiguration.setStore(storeRepository.getStoreById(creditConfigurationRequestDTO.getStore()));
        creditConfigurationRepository.save(creditConfiguration);
        var response = modelMapper.map(creditConfiguration, CreditConfigurationResponseDTO.class);

        return new ApiResponse<>("Credit Configuration created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<CreditConfigurationResponseDTO> updateCreditConfiguration(int id, CreditConfigurationRequestDTO creditConfigurationRequestDTO) {
        Optional<CreditConfiguration> creditConfigurationOptional = creditConfigurationRepository.findById(id);

        if (creditConfigurationOptional.isEmpty()) {
            return new ApiResponse<>("Credit Configuration not found", Estatus.ERROR, null);
        }else {
            CreditConfiguration creditConfiguration = creditConfigurationOptional.get();
            modelMapper.map(creditConfigurationRequestDTO, creditConfiguration);
            creditConfiguration.setStore(storeRepository.getStoreById(creditConfigurationRequestDTO.getStore()));
            creditConfigurationRepository.save(creditConfiguration);
            CreditConfigurationResponseDTO response = modelMapper.map(creditConfiguration, CreditConfigurationResponseDTO.class);
            return new ApiResponse<>("Credit Configuration updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteCreditConfiguration(int id) {
        Optional<CreditConfiguration> creditConfigurationOptional = creditConfigurationRepository.findById(id);

        if (creditConfigurationOptional.isEmpty()) {
            return new ApiResponse<>("Credit Configuration not found", Estatus.ERROR, null);
        }else {
            creditConfigurationRepository.deleteById(id);
            return new ApiResponse<>("Credit Configuration deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
