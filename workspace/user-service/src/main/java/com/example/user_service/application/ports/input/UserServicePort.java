package com.example.user_service.application.ports.input;

import com.example.user_service.domain.model.User;

import java.util.List;

public interface UserServicePort {
    User findById(Long id);

    List<User> findAll();

    User save(User user);

    User update(Long id, User user);

    void delete(Long id);
}
