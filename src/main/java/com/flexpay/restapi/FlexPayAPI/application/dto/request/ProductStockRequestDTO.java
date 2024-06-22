package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductStockRequestDTO {
    private double price;
    private int mountStock;
    private int productId;
    private int storeId;
    private int stateStockId;
}
