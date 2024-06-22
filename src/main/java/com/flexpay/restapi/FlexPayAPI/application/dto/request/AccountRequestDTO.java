package com.flexpay.restapi.FlexPayAPI.application.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequestDTO {
    private String email;
    private String userName;
    private int roleId;
}
