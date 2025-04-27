package com.myproject.transparentmoney.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
                @NotBlank(message = "Id is required") String id,
                String username,
                String password) {
}