package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementResponseDTO {
    private int id;
    private int capitalizationPeriod;
    private double interestRate;
    private int creditTerm;
    private int monthlyFees;
    private LocalDate paymentDay;
    private double discount;
    private double gracePeriod;
    private double initialFee;
    private InterestResponseDTO interest;
}
