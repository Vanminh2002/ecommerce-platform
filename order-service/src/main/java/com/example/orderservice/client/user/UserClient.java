package com.example.orderservice.client.user;

import com.example.orderservice.client.user.dto.UserDto;
import org.example.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/get-by-id/{id}")
    ApiResponse<UserDto> getById(@PathVariable("id") Long id);


}
