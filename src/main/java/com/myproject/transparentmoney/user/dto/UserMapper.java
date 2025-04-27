package com.myproject.transparentmoney.user.dto;

import org.mapstruct.*;

import com.myproject.transparentmoney.user.dto.request.UserUpdateRequest;
import com.myproject.transparentmoney.user.model.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    void updateUserFromDto(UserUpdateRequest dto, @MappingTarget User user);
}
