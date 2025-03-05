package com.myproject.transparentmoney.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.myproject.transparentmoney.user.model.User;

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
        // can modify based on what to return to API ui
        return ResponseEntity.ok(userService.findById(uuid).orElse(null));
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody User user) {
        boolean userExists = userService.userExists(user.getUsername(), user.getPassword());
        if (userExists) {
            return ResponseEntity.ok(new AuthResponse(true, "User authenticated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(false, "User authentication failed"));
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String uuid) {
        userService.deleteUserById(uuid);
        return ResponseEntity.ok().build();
    }
}
