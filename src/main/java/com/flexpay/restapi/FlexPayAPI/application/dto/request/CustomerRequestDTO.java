package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String dni;
    private String photoUrl;
    private int creditTerm;
    private int account;
}
