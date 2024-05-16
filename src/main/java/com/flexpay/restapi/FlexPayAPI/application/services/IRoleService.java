package com.flexpay.restapi.FlexPayAPI.application.services;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.RoleRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.RoleResponseDTO;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;

public interface IRoleService {

    ApiResponse<RoleResponseDTO> createRole(RoleRequestDTO roleRequestDTO);

    ApiResponse<RoleResponseDTO> updateRole(int id, RoleRequestDTO roleRequestDTO);

    ApiResponse<Void> deleteRole(int id);
}
