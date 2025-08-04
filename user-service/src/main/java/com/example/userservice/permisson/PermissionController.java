package com.example.userservice.permisson;

import com.example.userservice.dto.ApiResponse;
import com.example.userservice.permisson.permission.request.CreatePermissionRequest;
import com.example.userservice.permisson.permission.response.PermissionResponse;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/permissions")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("create")
    ApiResponse<PermissionResponse> create(@RequestBody CreatePermissionRequest request) {
        PermissionResponse response = permissionService.createPermission(request);
        if (response != null) {
            return ApiResponse.success(response);
        }
        return ApiResponse.error(500, "Lỗi khi tạo quyền");
    }


}
