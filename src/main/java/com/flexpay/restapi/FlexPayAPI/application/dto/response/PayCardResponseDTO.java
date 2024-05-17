package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import com.flexpay.restapi.FlexPayAPI.domain.entities.PaymentMethod;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayCardResponseDTO {
    private int id;
    private double payAmount;
    private PaymentMethod paymentMethod;
}
