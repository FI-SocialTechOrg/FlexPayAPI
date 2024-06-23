package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestRequestDTO {
    private double rate;
    private int creditConfiguration;
    private int payInterest;
    private int typeInterest;
    private int capitalizationPeriod;
}
