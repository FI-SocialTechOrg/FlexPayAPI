package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import com.flexpay.restapi.FlexPayAPI.domain.entities.PaymentMethod;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayCardRequestDTO {
    private double payAmount;
    private int paymentMethod;
}
