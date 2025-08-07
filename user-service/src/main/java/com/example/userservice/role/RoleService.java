package com.example.userservice.role;

import com.example.userservice.permisson.Permission;
import com.example.userservice.role.role.request.CreateRoleRequest;
import com.example.userservice.role.role.response.RoleResponse;
import com.example.userservice.exception.AppException;
import com.example.userservice.exception.ErrorCode;
import com.example.userservice.permisson.PermissionMapper;
import com.example.userservice.repository.CommonRepositoryCustom;
import com.example.userservice.permisson.PermissionRepository;
import com.example.userservice.rolePermission.RolePermission;
import com.example.userservice.rolePermission.RolePermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private PermissionRepository permissionRepository;

    @Resource
    private RolePermissionRepository rolePermissionRepository;

    @Resource
    private CommonRepositoryCustom commonRepositoryCustom;

    public RoleResponse createRole(CreateRoleRequest request) {
//        return commonRepositoryCustom.createRoleWithPermission(request);
        if (roleRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        if (permissionRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        Role role = roleMapper.toDto(request);


        Set<Permission> permissions = new HashSet<>();
        for (Long permissionId : request.getPermissionId()) {
            Permission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(()->new AppException(ErrorCode.NOT_FOUND));
            permissions.add(permission);
        }
        role.setPermissionId(permissions.stream().map(Permission::getId).collect(Collectors.toSet()));
        roleRepository.save(role);
        var roleId = role.getId();

        for (Permission permission : permissions) {
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(permission.getId());
            rolePermissionRepository.save(rp);
        }

        return roleMapper.toResponse(role, new ArrayList<>(permissions));
    }

    public List<RoleResponse> getAll() {
        List<Role> listRole = roleRepository.findAll();
        if (listRole.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        List<RoleResponse> responseList = new ArrayList<>();

        for (Role role : listRole) {
            List<Permission> permissions = permissionRepository.findPermissionsByRoleId(role.getId());
            RoleResponse response = roleMapper.toResponse(role, permissions);
            responseList.add(response);
        }

        return responseList;
    }


    public void deleteRoleById(Long id) {
        var role = roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        roleRepository.deleteById(id);
    }


}
