package com.flexpay.restapi.FlexPayAPI.application.dto.request;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayMovementRequestDTO {
    private double payAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateLimit;
    private int paymentMethod;
    private int movement;
}
