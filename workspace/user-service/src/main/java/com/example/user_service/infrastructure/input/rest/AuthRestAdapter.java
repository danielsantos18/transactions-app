package com.example.user_service.infrastructure.input.rest;

import com.example.user_service.application.ports.input.UserServicePort;
import com.example.user_service.infrastructure.input.rest.mapper.UserRestMapper;
import com.example.user_service.infrastructure.input.rest.model.request.AuthRequest;
import com.example.user_service.infrastructure.input.rest.model.request.UserRequest;
import com.example.user_service.infrastructure.input.rest.model.response.AuthResponse;
import com.example.user_service.infrastructure.input.rest.model.response.UserResponse;
import com.example.user_service.infrastructure.util.JwtUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestAdapter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserServicePort userServicePort;
    private final UserRestMapper userRestMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest Request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRestMapper.toUserResponse(userServicePort
                        .save(userRestMapper.toUser(Request))));
    }

}
