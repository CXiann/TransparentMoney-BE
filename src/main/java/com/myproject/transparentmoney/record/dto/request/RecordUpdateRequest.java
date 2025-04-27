package com.myproject.transparentmoney.record.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RecordUpdateRequest(
        @NotBlank(message = "Id is required") String id,
        Double amount,
        String category,
        String description) {
}