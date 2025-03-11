package com.myproject.transparentmoney.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank(message = "Id is required") String id,
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password) {
}