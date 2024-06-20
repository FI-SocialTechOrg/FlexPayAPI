package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.TypeInterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.TypeInterestResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.ITypeInterestService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.TypeInterest;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.ITypeInterestRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeInterestService implements ITypeInterestService {
    private final ITypeInterestRepository typeInterestRepository;
    private final ModelMapper modelMapper;

    public TypeInterestService(ITypeInterestRepository typeInterestRepository, ModelMapper modelMapper) {
        this.typeInterestRepository = typeInterestRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<TypeInterestResponseDTO> getTypeInterestById(int id) {
        Optional<TypeInterest> typeInterestOptional = typeInterestRepository.findById(id);
        if (typeInterestOptional.isPresent()){
            TypeInterest typeInterest = typeInterestOptional.get();
            TypeInterestResponseDTO responseDTO = modelMapper.map(typeInterest, TypeInterestResponseDTO.class);

            return new ApiResponse<>("Interest fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Interest not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<TypeInterestResponseDTO> createTypeInterest(TypeInterestRequestDTO typeInterestRequestDTO) {
        var typeInterest = modelMapper.map(typeInterestRequestDTO, TypeInterest.class);
        typeInterestRepository.save(typeInterest);
        var response = modelMapper.map(typeInterest, TypeInterestResponseDTO.class);

        return new ApiResponse<>("Interest created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<TypeInterestResponseDTO> updateTypeInterest(int id, TypeInterestRequestDTO typeInterestRequestDTO) {
        Optional<TypeInterest> typeInterestOptional = typeInterestRepository.findById(id);
        if (typeInterestOptional.isPresent()){
            TypeInterest typeInterest = typeInterestOptional.get();
            modelMapper.map(typeInterestRequestDTO, typeInterest);
            typeInterestRepository.save(typeInterest);
            TypeInterestResponseDTO responseDTO = modelMapper.map(typeInterest, TypeInterestResponseDTO.class);

            return new ApiResponse<>("Interest updated successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Interest not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<Void> deleteTypeInterest(int id) {
        Optional<TypeInterest> typeInterestOptional = typeInterestRepository.findById(id);

        if (typeInterestOptional.isEmpty()) {
            return new ApiResponse<>("PayCard not found", Estatus.ERROR, null);
        }else {
            typeInterestRepository.deleteById(id);
            return new ApiResponse<>("PayCard deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
