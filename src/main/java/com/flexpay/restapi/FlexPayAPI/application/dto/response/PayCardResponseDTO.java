package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import com.flexpay.restapi.FlexPayAPI.domain.entities.PaymentMethod;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayCardResponseDTO {
    private int id;
    private double payAmount;
    private LocalDate dateLimit;
    private List<PaymentMethodResponseDTO> paymentMethods;
}
