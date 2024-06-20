package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductStockWithStoreResponseDTO {
    // This class is a DTO that represents the response of a product stock with store
    private int id;
    private double price;
    private int mountStock;
    private ProductResponseDTO product;
    private StateStockResponseDTO stateStock;
    private StoreWithoutProductStockResponseDTO store;
}
