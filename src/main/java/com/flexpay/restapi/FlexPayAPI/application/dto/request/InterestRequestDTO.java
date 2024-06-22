package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestRequestDTO {
    private double rate;
    private int creditConfigurationId;
    private int payInterestId;
    private int typeInterestId;
}
