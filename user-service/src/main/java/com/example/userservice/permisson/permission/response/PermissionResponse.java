package com.example.userservice.permisson.permission.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponse {
    private Long id;
    private String name;
    private String description;
}
