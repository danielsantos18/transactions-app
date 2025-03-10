package com.example.user_service.infrastructure.output.persistence;

import com.example.user_service.application.ports.output.UserPersistencePort;
import com.example.user_service.domain.model.User;
import com.example.user_service.infrastructure.output.persistence.mapper.UserPersistenceMapper;
import com.example.user_service.infrastructure.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public List<User> findAll() {
        return userPersistenceMapper.toUsers(userRepository.findAll());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(userPersistenceMapper::toUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userPersistenceMapper::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userPersistenceMapper::toUser);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .map(userPersistenceMapper::toUser);
    }

    @Override
    public User save(User user) {

        return userPersistenceMapper
                .toUser(userRepository
                        .save(userPersistenceMapper.toUserEntity(user)));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
