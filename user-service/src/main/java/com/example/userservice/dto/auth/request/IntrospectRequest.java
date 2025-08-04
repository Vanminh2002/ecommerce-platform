package com.example.userservice.dto.auth.request;

import lombok.Data;

@Data
public class IntrospectRequest {
    private String token;
}
