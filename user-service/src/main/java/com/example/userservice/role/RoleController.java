package com.example.userservice.role;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.role.role.request.CreateRoleRequest;
import com.example.userservice.role.role.response.RoleResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
    @Resource
    private RoleService roleService;


    @PostMapping("create")
    ApiResponse<RoleResponse> createRole(@RequestBody CreateRoleRequest request) {
        RoleResponse response = roleService.createRole(request);
        return ApiResponse.success(response);
    }


    @GetMapping("get-all")
    ApiResponse<List<RoleResponse>> getAll() {
        List<RoleResponse> response = roleService.getAll();
        return ApiResponse.success(response);
    }


    @DeleteMapping("delete/{id}")
    ApiResponse<String> deleterole(@PathVariable Long id) {
        roleService.deleteRoleById(id);
        return ApiResponse.<String>builder()
                .message("Xóa Role thành công")
                .build();
    }
}
