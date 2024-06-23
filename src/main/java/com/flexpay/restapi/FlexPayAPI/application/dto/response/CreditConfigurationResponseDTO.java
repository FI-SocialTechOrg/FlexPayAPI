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
    private double maxCredit;
    private double monthlyFee;
    private double discount;
    private int gracePeriod;
    private int graceType;
    private int initialFee;
    private StoreWithoutProductStockResponseDTO store;
    private List<InterestResponseDTO> interests;
}
