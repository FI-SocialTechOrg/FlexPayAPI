package com.flexpay.restapi.FlexPayAPI.application.dto.request;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementRequestDTO {
    private int capitalizationPeriod;
    private double interestRate;
    private int creditTerm;
    private int monthlyFees;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDay;
    private double discount;
    private double gracePeriod;
    private double initialFee  ;
    private int creditCard;
    private int interest;
}
