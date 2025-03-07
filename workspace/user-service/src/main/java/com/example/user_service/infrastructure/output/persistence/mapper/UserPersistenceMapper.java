package com.example.user_service.infrastructure.output.persistence.mapper;

import com.example.user_service.domain.model.User;
import com.example.user_service.infrastructure.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    UserEntity toUserEntity(User user);

    User toUser(UserEntity entity);

    List<User> toUsers(List<UserEntity> entityList);
}
