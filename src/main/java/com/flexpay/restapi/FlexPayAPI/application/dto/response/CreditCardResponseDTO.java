package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardResponseDTO {
    private int id;
    private double maxCredit;
    private double initialCredit;
    private double balance;
    private double purchase;
    private double purchaseInterest;
    private double debt;
    private PayCardResponseDTO payCard;
    private StateCardResponseDTO stateCard;
    private ShoppingCartResponseDTO shoppingCart;
}
