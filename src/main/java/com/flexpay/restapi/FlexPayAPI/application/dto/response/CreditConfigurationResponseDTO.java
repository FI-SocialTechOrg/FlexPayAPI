package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditConfigurationResponseDTO {
    private int id;
    private int capitalizationPeriod;
    private double monthlyFee;
    private double discount;
    private int gracePeriod;
    private int initialFee;
    private StoreResponseDTO store;
    private List<InterestResponseDTO> interests;
}
