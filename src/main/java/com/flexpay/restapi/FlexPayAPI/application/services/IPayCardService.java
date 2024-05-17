package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PayCardRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PayCardResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IPayCardService {
    ApiResponse<PayCardResponseDTO> getPayCardById(int id);
    ApiResponse<PayCardResponseDTO> createPayCard(PayCardRequestDTO payCardRequestDTO);
    ApiResponse<PayCardResponseDTO> updatePayCard(int id, PayCardRequestDTO payCardRequestDTO);
    ApiResponse<Void> deletePayCard(int id);

}
