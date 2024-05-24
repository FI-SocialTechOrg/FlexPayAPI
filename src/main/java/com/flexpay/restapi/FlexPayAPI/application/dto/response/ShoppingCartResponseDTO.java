package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartResponseDTO {
    private int id;
    private double total;
    private CustomerResponseDTO customer;
    private ShoppingStateResponseDTO shoppingState;
    private List<ShoppingStockResponseDTO> shoppingStocks;
}
