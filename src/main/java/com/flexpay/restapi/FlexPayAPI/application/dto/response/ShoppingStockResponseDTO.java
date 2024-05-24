package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingStockResponseDTO {
    private int id;
    private int quantity;
    private ProductStockResponseDTO productStock;
    private ShoppingCartResponseDTO shoppingCart;
}
