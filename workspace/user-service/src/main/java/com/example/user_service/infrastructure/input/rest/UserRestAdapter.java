package com.example.user_service.infrastructure.input.rest;

import com.example.user_service.application.ports.input.UserServicePort;
import com.example.user_service.infrastructure.input.rest.mapper.UserRestMapper;
import com.example.user_service.infrastructure.input.rest.model.request.UserRequest;
import com.example.user_service.infrastructure.input.rest.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestAdapter {

    private final UserServicePort userServicePort;
    private final UserRestMapper userRestMapper;

    @GetMapping("/list")
    public List<UserResponse> findAll() {
        return userRestMapper.toUserResponseList(userServicePort.findAll());
    }

    @GetMapping("/find/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userRestMapper.toUserResponse(userServicePort.findById(id));
    }

    @GetMapping("/find/{username}")
    public UserResponse findByUsername(@PathVariable String username) {
        return userRestMapper.toUserResponse(userServicePort.findByUsername(username));
    }

    @PutMapping("/update/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserRequest Request) {
        return userRestMapper.toUserResponse(userServicePort
                .update(id, userRestMapper.toUser(Request)));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userServicePort.delete(id);
    }
}
