package com.example.user_service.infrastructure.output.persistence.mapper.impl;

import com.example.user_service.domain.model.User;
import com.example.user_service.infrastructure.output.persistence.entity.UserEntity;
import com.example.user_service.infrastructure.output.persistence.mapper.UserPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPersistenceMapperImpl implements UserPersistenceMapper {

    @Override
    public UserEntity toUserEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id(user.getId());
        userEntity.username(user.getUsername());
        userEntity.email(user.getEmail());
        userEntity.password(user.getPassword());
        userEntity.phone(user.getPhone());
        userEntity.role(user.getRole());

        return userEntity.build();
    }

    @Override
    public User toUser(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User.UserBuilder user = User.builder();
        user.id(entity.getId());
        user.username(entity.getUsername());
        user.email(entity.getEmail());
        user.password(entity.getPassword());
        user.phone(entity.getPhone());
        user.role(entity.getRole());

        return user.build();
    }

    @Override
    public List<User> toUsers(List<UserEntity> entityList) {
        if (entityList == null) {
            return null;
        }

        List<User> list = new ArrayList<>(entityList.size());
        for (UserEntity userEntity : entityList) {
            list.add(toUser(userEntity));
        }

        return list;
    }
}
