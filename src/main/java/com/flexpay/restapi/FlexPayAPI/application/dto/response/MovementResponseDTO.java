package com.flexpay.restapi.FlexPayAPI.application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementResponseDTO {
    private int id;
    private int creditTerm;
    private LocalDate paymentDay;
    private LocalDate dateMovement;
    private CreditConfigurationResponseDTO creditConfiguration;
    private List<PayMovementResponseDTO> payMovements;
}
