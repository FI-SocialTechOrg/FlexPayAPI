package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Customer;
import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingState;
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
