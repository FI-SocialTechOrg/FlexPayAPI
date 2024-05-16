package com.flexpay.restapi.FlexPayAPI.application.controller;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.RoleRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.RoleResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.implementation.RoleService;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Role", description = "Role API")
@RestController
@RequestMapping("/api/v1/servifix")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "create a new role")
    @PostMapping("/role")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        var res = roleService.createRole(roleRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "update an existing role")
    @PutMapping("/role/{id}")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> updateRole(@PathVariable int id, @RequestBody RoleRequestDTO roleRequestDTO) {
        var res = roleService.updateRole(id, roleRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "delete a role")
    @DeleteMapping("/role/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable int id) {
        var res = roleService.deleteRole(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }



}
