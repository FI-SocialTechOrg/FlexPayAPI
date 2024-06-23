package com.flexpay.restapi.FlexPayAPI.application.dto.request;



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
    private String dni;
    private String ruc;
    private String companyName;
    private String imageUrl;
    private int account;
}
