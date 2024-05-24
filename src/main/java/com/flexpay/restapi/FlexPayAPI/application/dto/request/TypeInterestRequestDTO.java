package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeInterestRequestDTO {
    private String type;
    private String description;
}
