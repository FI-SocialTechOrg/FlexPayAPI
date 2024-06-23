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
    // This class is a DTO that represents the response of a product stock with store
    private int id;
    private double price;
    private int mountStock;
    private ProductResponseDTO product;
    private StoreWithoutProductStockResponseDTO store;
    private StateStockResponseDTO stateStock;
}
