package com.myproject.transparentmoney.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private boolean authenticated;
    private String message;
}
