package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.TypeInterestRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.TypeInterestResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface ITypeInterestService {
    ApiResponse<TypeInterestResponseDTO> getTypeInterestById(int id);
    ApiResponse<TypeInterestResponseDTO> createTypeInterest(TypeInterestRequestDTO typeInterestRequestDTO);
    ApiResponse<TypeInterestResponseDTO> updateTypeInterest(int id, TypeInterestRequestDTO typeInterestRequestDTO);
    ApiResponse<Void> deleteTypeInterest(int id);
}
