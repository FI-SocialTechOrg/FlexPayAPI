package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreWithoutProductStockResponseDTO {
    // This class is used to represent the response of a store without product stock
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String dni;
    private String ruc;
    private String companyName;
    private AccountResponseDTO account;
}
