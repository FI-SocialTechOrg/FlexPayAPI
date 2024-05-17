package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.PaymentMethodRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.PaymentMethodResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IPaymentMethodService {
    ApiResponse<PaymentMethodResponseDTO> getPaymentMethodById(int id);
    ApiResponse<PaymentMethodResponseDTO> createPaymentMethod(PaymentMethodRequestDTO paymentMethodRequestDTO);
    ApiResponse<PaymentMethodResponseDTO> updatePaymentMethod(int id, PaymentMethodRequestDTO paymentMethodRequestDTO);
    ApiResponse<Void> deletePaymentMethod(int id);
}
