package com.example.user_service.infrastructure.input.rest.mapper;

import com.example.user_service.domain.model.User;
import com.example.user_service.infrastructure.input.rest.model.request.UserRequest;
import com.example.user_service.infrastructure.input.rest.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {

    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> userList);

}
