package com.myproject.transparentmoney.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserSettingUpdateRequest(
                @NotBlank(message = "Language is required") String language) {

}
