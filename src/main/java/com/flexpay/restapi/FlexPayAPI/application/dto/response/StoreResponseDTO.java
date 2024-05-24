package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String dni;
    private String ruc;
    private String companyName;
    private AccountResponseDTO account;
    private List<ProductStockResponseDTO> productStocks;
}
