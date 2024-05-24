package com.flexpay.restapi.FlexPayAPI.application.services.implementation;

import com.flexpay.restapi.FlexPayAPI.application.dto.request.RoleRequestDTO;
import com.flexpay.restapi.FlexPayAPI.application.dto.response.RoleResponseDTO;
import com.flexpay.restapi.FlexPayAPI.application.services.IRoleService;
import com.flexpay.restapi.FlexPayAPI.domain.entities.Role;
import com.flexpay.restapi.FlexPayAPI.infraestructure.repositories.IRoleRepository;
import com.flexpay.restapi.shared.model.dto.response.ApiResponse;
import com.flexpay.restapi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(IRoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Role getRoleById(int id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.orElse(null);
    }

    @Override
    public ApiResponse<RoleResponseDTO> createRole(RoleRequestDTO roleRequestDTO) {
        var role = modelMapper.map(roleRequestDTO, Role.class);
        roleRepository.save(role);

        var response = modelMapper.map(role, RoleResponseDTO.class);

        return new ApiResponse<>("Role created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<RoleResponseDTO> updateRole(int id, RoleRequestDTO roleRequestDTO) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()) {
            return new ApiResponse<>("Role not found", Estatus.ERROR, null);
        } else {
            Role role = roleOptional.get();
            modelMapper.map(roleRequestDTO, role);
            roleRepository.save(role);
            RoleResponseDTO response = modelMapper.map(role, RoleResponseDTO.class);
            return new ApiResponse<>("Role updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteRole(int id) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()) {
            return new ApiResponse<>("Role not found", Estatus.ERROR, null);
        } else {
            roleRepository.deleteById(id);
            return new ApiResponse<>("Role deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
