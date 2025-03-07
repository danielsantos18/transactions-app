package com.example.user_service.infrastructure.input.rest.mapper.impl;

import com.example.user_service.domain.model.User;
import com.example.user_service.infrastructure.input.rest.mapper.UserRestMapper;
import com.example.user_service.infrastructure.input.rest.model.request.UserRequest;
import com.example.user_service.infrastructure.input.rest.model.response.UserResponse;
import com.example.user_service.infrastructure.util.ApiResponse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRestMapperImpl implements UserRestMapper {

    @Override
    public User toUser(UserRequest request) {

        if (request == null) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id(request.getId());
        user.username(request.getUsername());
        user.email(request.getEmail());
        user.password(request.getPassword());
        user.phone(request.getPhone());

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id(user.getId());
        userResponse.username(user.getUsername());
        userResponse.email(user.getEmail());
        userResponse.password(user.getPassword());
        userResponse.phone(user.getPhone());
        userResponse.role(user.getRole());

        return userResponse.build();
    }

    public UserResponse toUserResponse(ApiResponse<User> apiResponse) {
        if (apiResponse == null || apiResponse.getData() == null) {
            return null;
        }
        return toUserResponse(apiResponse.getData());
    }

    @Override
    public List<UserResponse> toUserResponseList(List<User> userList) {
        if (userList == null) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>(userList.size());
        for (User user : userList) {
            list.add(toUserResponse(user));
        }
        return list;
    }
}