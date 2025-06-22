package com.myproject.transparentmoney.auth;

import com.myproject.transparentmoney.auth.dto.request.AuthLoginRequest;
import com.myproject.transparentmoney.auth.dto.response.AuthJwtResponse;
import com.myproject.transparentmoney.auth.jwt.JwtTokenUtil;
import com.myproject.transparentmoney.user.UserService;
import com.myproject.transparentmoney.user.dto.request.UserCreateRequest;
import com.myproject.transparentmoney.user.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        String token = jwtTokenUtil.generateToken(req.username());
        return ResponseEntity.ok(new AuthJwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserCreateRequest userRequest) {
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }
}
