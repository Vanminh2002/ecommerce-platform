package com.example.userservice.exception;

import com.example.userservice.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlerRuntimeException(RuntimeException exception) {

        ApiResponse response = new ApiResponse();
        response.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        response.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {
            errorCode = ErrorCode.VALIDATION_FAILED;
        }


        ApiResponse response = new ApiResponse();
        response.setMessage(errorCode.getMessage());
        response.setCode(errorCode.getCode());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlerAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse response = new ApiResponse();
        response.setMessage(errorCode.getMessage());
        response.setCode(errorCode.getCode());
        return ResponseEntity.badRequest().body(response);
    }


}
