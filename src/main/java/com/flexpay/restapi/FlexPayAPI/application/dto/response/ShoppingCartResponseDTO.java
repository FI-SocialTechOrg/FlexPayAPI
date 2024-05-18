package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartResponseDTO {
    private int id;
    private double total;
    private CustomerResponseDTO customer;
    private ShoppingStateResponseDTO shoppingState;
}
