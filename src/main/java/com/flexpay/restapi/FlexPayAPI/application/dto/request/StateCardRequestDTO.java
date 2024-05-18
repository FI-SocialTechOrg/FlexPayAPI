package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateCardRequestDTO {
    private String name;
    private String description;
}
