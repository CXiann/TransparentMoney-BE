package com.myproject.transparentmoney.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.transparentmoney.user.dto.AuthResponse;
import com.myproject.transparentmoney.user.dto.request.UserSettingUpdateRequest;
import com.myproject.transparentmoney.user.dto.request.UserCreateRequest;
import com.myproject.transparentmoney.user.dto.request.UserUpdateRequest;
import com.myproject.transparentmoney.user.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<User> findById(@PathVariable String uuid) {
        return ResponseEntity.ok(userService.findById(uuid));
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserCreateRequest userRequest) {
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody UserCreateRequest userRequest) {
        AuthResponse authResponse = userService.isAuthenticated(userRequest.username(), userRequest.password());
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/language/{uuid}")
    public ResponseEntity<User> changeLanguage(@Valid @RequestBody UserSettingUpdateRequest languageRequest,
            @PathVariable String uuid) {
        return ResponseEntity.ok(userService.changeLanguage(languageRequest, uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String uuid) {
        userService.deleteUserById(uuid);
        return ResponseEntity.ok().build();
    }
}
