package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardRequestDTO {
    private double maxCredit;
    private double initialCredit;
    private double balance;
    private double purchase;
    private double purchaseInterest;
    private double debt;
    private int payCard;
    private int stateCard;
    private int shoppingCart;
}
