package com.flexpay.restapi.FlexPayAPI.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestResponseDTO {
    private int id;
    private String type;
    private String description;
}
