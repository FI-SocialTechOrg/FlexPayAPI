package com.flexpay.restapi.FlexPayAPI.application.dto.request;


import com.flexpay.restapi.FlexPayAPI.domain.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreRequestDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String DNI;
    private String RUC;
    private String companyName;
    private int account;
}
