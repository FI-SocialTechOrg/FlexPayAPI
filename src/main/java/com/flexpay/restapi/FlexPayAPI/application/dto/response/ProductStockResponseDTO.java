package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductStockResponseDTO {
    private int id;
    private double price;
    private int mountStock;
    private ProductResponseDTO product;
    private StoreResponseDTO store;
    private StateStockResponseDTO stateStock;
}
