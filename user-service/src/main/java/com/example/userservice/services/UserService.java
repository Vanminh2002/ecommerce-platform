package com.example.userservice.services;

import com.example.userservice.dto.user.request.UpdateUserRequest;
import com.example.userservice.dto.user.request.UserCreateRequest;
import com.example.userservice.dto.user.response.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.exception.AppException;
import com.example.userservice.exception.ErrorCode;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.role.Role;
import com.example.userservice.role.RoleRepository;
import com.example.userservice.userRole.UserRole;
import com.example.userservice.userRole.UserRoleRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.BaseException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserService {
    //    @Resource
//    private ModelMapper modelMapper;
    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private UserRoleRepository userRoleRepository;



    @Resource
    private UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest request) {
        try {
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new AppException(ErrorCode.USER_EXISTS);
            }
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new AppException(ErrorCode.EMAIL_EXISTS);
            }
            if (userRepository.existsByPhone(request.getPhone())) {
                throw new AppException(ErrorCode.PHONE_EXISTS);
            }
            System.out.println("Request password (raw): " + request.getPassword());
            var user = userMapper.toDto(request);
            System.out.println("Before encode, user.getPassword(): " + user.getPassword());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            System.out.println("After encode, user.getPassword(): " + user.getPassword());

            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

            user.setRoleId(role.getId());

            user = userRepository.save(user);

            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());

            userRole = userRoleRepository.save(userRole);

            return userMapper.toResponse(user);

        } catch (Exception e) {
            log.warn("error", e);
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
//        if (user.isEmpty()) {
//            throw new AppException(ErrorCode.USER_NOT_FOUND);
//        }
        log.warn("Danh sách user: {}", user.toString());
        return userMapper.toResponse(user);
//        return modelMapper.map(user, UserResponse.class);
    }

    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.toUpdateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var save = userRepository.save(user);
        return userMapper.toResponse(save);
//        modelMapper.map(request, user);
//        return modelMapper.map(userRepository.save(user), UserResponse.class);
    }

    public List<UserResponse> getAll() {
        var user = userRepository.findAll();
        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return user.stream().map(userMapper::toResponse).collect(Collectors.toList());
//        return user
//                .stream()
//                .map(u -> modelMapper
//                        .map(u, UserResponse.class)).toList();
    }


    public void deleteUser(Long id) {
        var user = getById(id);
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
