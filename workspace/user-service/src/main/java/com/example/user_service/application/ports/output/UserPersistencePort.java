package com.example.user_service.application.ports.output;

import com.example.user_service.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    List<User> findAll();

    User save(User user);

    void delete(Long id);
}
