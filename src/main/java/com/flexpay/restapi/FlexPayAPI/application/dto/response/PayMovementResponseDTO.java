package com.flexpay.restapi.FlexPayAPI.application.dto.response;


import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayMovementResponseDTO {
    private int id;
    private double payAmount;
    private LocalDate dateLimit;
    private PaymentMethodResponseDTO paymentMethod;
}
