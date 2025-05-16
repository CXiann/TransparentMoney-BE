package com.myproject.transparentmoney.user;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myproject.transparentmoney.common.exception.custom.UnauthorizedException;
import com.myproject.transparentmoney.user.dto.AuthResponse;
import com.myproject.transparentmoney.user.dto.UserMapper;
import com.myproject.transparentmoney.user.dto.request.UserSettingUpdateRequest;
import com.myproject.transparentmoney.user.dto.request.UserCreateRequest;
import com.myproject.transparentmoney.user.dto.request.UserUpdateRequest;
import com.myproject.transparentmoney.user.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String uuid) {
        UUID uuidObj = UUID.fromString(uuid);
        return userRepository.findById(uuidObj)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID " + uuid + " not found"));
    }

    public User saveUser(UserCreateRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.username());
        user.setPassword(userRequest.password());

        User savedUser = userRepository.save(user);

        log.info("User with id: {} saved successfully", savedUser.getId());
        return savedUser;
    }

    public User updateUser(UserUpdateRequest userRequest) {
        User user = findById(userRequest.id());

        userMapper.updateUserFromDto(userRequest, user);
        user.setUpdatedAt(OffsetDateTime.now());

        User updatedUser = userRepository.save(user);

        log.info("User with id: {} updated successfully", user.getId());
        return updatedUser;
    }

    public User changeLanguage(UserSettingUpdateRequest languageRequest, String uuid) {
        User user = findById(uuid);

        userMapper.updateUserSettingFromDto(languageRequest, user.getSettings());
        user.setUpdatedAt(OffsetDateTime.now());

        User updatedUser = userRepository.save(user);

        log.info("User with id: {} updated successfully - language", user.getId());
        return updatedUser;
    }

    public void deleteUserById(String uuid) {
        try {
            UUID uuidObj = UUID.fromString(uuid);

            if (!userRepository.existsById(uuidObj)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + uuid + " not found");
            }

            userRepository.deleteById(uuidObj);
            log.info("User with id: {} removed successfully", uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + uuid);
        }
    }

    public boolean userExists(String username, String password) {
        List<User> users = userRepository.findByUsername(username);
        boolean userExists = users.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
        return userExists;
    }

    public AuthResponse isAuthenticated(String username, String password) {
        if (userExists(username, password)) {
            return new AuthResponse(true, "User authenticated successfully");
        } else {
            throw new UnauthorizedException(username);
        }
    }
}
