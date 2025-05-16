package com.myproject.transparentmoney.user.dto;

import org.mapstruct.*;

import com.myproject.transparentmoney.user.dto.request.UserSettingUpdateRequest;
import com.myproject.transparentmoney.user.dto.request.UserUpdateRequest;
import com.myproject.transparentmoney.user.model.User;
import com.myproject.transparentmoney.user.model.UserSettings;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    void updateUserFromDto(UserUpdateRequest dto, @MappingTarget User user);

    void updateUserSettingFromDto(UserSettingUpdateRequest dto, @MappingTarget UserSettings userSetting);

}
