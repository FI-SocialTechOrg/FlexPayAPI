package com.flexpay.restapi.FlexPayAPI.application.dto.request;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartRequestDTO {
    private double total;
    private int customer;
    private int shoppingState;
}
