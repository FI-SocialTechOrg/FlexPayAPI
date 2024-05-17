package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Customer;
import com.flexpay.restapi.FlexPayAPI.domain.entities.ShoppingState;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartResponseDTO {
    private int id;
    private double total;
    private Customer customer;
    private ShoppingState shoppingState;
}
