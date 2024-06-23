package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestResponseDTO {
    private int id;
    private double rate;
    private PayInterestResponseDTO payInterest;
    private TypeInterestResponseDTO typeInterest;
    private CapitalizationPeriodResponseDTO capitalizationPeriod;
}
