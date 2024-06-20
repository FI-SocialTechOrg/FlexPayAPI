package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodRequestDTO {
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePay;
    private double mount;
    private String cardNumber;
}
