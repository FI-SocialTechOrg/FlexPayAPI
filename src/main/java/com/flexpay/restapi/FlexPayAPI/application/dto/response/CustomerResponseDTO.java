package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
    private LocalDate birthday;
    private String dni;
    private String photoUrl;
    private AccountResponseDTO account;
}
