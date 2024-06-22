package com.flexpay.restapi.FlexPayAPI.application.dto.request;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementRequestDTO {
    private int creditTerm;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateMovement;
    private int creditCardId;
    private int creditConfigurationId;
}
