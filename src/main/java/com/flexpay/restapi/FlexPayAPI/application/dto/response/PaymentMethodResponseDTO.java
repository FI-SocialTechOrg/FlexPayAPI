package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import com.flexpay.restapi.FlexPayAPI.domain.entities.PayCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodResponseDTO {
    private int id;
    private String type;
    private double mount;
    private String cardNumber;
}
