package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Store;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditConfigurationRequestDTO {
    private double maxCredit;
    private double monthlyFee;
    private double discount;
    private int gracePeriod;
    private int initialFee;
    private int store;
}
