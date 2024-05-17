package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import com.flexpay.restapi.FlexPayAPI.domain.entities.Account;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String DNI;
    private String RUC;
    private String companyName;
    private Account account;
}
