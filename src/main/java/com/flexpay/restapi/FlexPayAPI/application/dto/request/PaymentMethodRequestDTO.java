package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodRequestDTO {
    private String type;
    private LocalDate datePay;
    private double mount;
    private String cardNumber;
    private int payCard;
}
