package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.CapitalizationPeriodRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.CapitalizationPeriodResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.ICapitalizationPeriodService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.CapitalizationPeriod;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.ICapitalizationPeriodRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CapitalizationPeriodService implements ICapitalizationPeriodService {
    private final ICapitalizationPeriodRepository capitalizationPeriodRepository;
    private final ModelMapper modelMapper;

    public CapitalizationPeriodService(ICapitalizationPeriodRepository capitalizationPeriodRepository, ModelMapper modelMapper) {
        this.capitalizationPeriodRepository = capitalizationPeriodRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<CapitalizationPeriodResponseDTO> getCapitalizationPeriodById(int id) {
        Optional<CapitalizationPeriod> capitalizationPeriodOptional = capitalizationPeriodRepository.findById(id);
        if (capitalizationPeriodOptional.isPresent()){
            CapitalizationPeriod capitalizationPeriod = capitalizationPeriodOptional.get();
            CapitalizationPeriodResponseDTO responseDTO = modelMapper.map(capitalizationPeriod, CapitalizationPeriodResponseDTO.class);

            return new ApiResponse<>("Capitalization period fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Capitalization period not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<CapitalizationPeriodResponseDTO> createCapitalizationPeriod(CapitalizationPeriodRequestDTO capitalizationPeriodRequestDTO) {
        var capitalizationPeriod = modelMapper.map(capitalizationPeriodRequestDTO, CapitalizationPeriod.class);
        capitalizationPeriodRepository.save(capitalizationPeriod);
        var response = modelMapper.map(capitalizationPeriod, CapitalizationPeriodResponseDTO.class);

        return new ApiResponse<>("Capitalization period created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<CapitalizationPeriodResponseDTO> updateCapitalizationPeriod(int id, CapitalizationPeriodRequestDTO capitalizationPeriodRequestDTO) {
        Optional<CapitalizationPeriod> capitalizationPeriodOptional = capitalizationPeriodRepository.findById(id);
        if (capitalizationPeriodOptional.isPresent()){
            CapitalizationPeriod capitalizationPeriod = capitalizationPeriodOptional.get();
            modelMapper.map(capitalizationPeriodRequestDTO, capitalizationPeriod);
            capitalizationPeriodRepository.save(capitalizationPeriod);
            CapitalizationPeriodResponseDTO responseDTO = modelMapper.map(capitalizationPeriod, CapitalizationPeriodResponseDTO.class);

            return new ApiResponse<>("Capitalization period updated successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Capitalization period not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<Void> deleteCapitalizationPeriod(int id) {
        Optional<CapitalizationPeriod> capitalizationPeriodOptional = capitalizationPeriodRepository.findById(id);

        if (capitalizationPeriodOptional.isEmpty()) {
            return new ApiResponse<>("PayCard not found", Estatus.ERROR, null);
        }else {
            capitalizationPeriodRepository.deleteById(id);
            return new ApiResponse<>("PayCard deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
