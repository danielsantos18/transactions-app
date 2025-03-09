package com.example.user_service.application.service;

import com.example.user_service.application.ports.input.UserServicePort;
import com.example.user_service.application.ports.output.UserPersistencePort;
import com.example.user_service.domain.exception.UserNotFoundException;
import com.example.user_service.domain.exception.ValidationException;
import com.example.user_service.domain.model.Role;
import com.example.user_service.domain.model.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (userPersistencePort.findByEmail(user.getEmail()).isPresent()) {
            throw new ValidationException("El correo ya está registrado");
        }

        if (userPersistencePort.findByPhone(user.getPhone()).isPresent()) {
            throw new ValidationException("El teléfono ya está registrado");
        }

        // Asignar el rol USER si no se especifica
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        // Encripta la contraseña
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userPersistencePort.save(user);
    }

    @Override
    public User findById(Long id) {
        return userPersistencePort.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userPersistencePort.findAll();
    }

    @Override
    public User update(Long id, User user) {
        return userPersistencePort.findById(id).map(savedUser -> {

            // Validar si el correo ya está en uso por otro usuario
            Optional<User> existingByEmail = userPersistencePort.findByEmail(user.getEmail());
            if (existingByEmail.isPresent() && !existingByEmail.get().getId().equals(id)) {
                throw new IllegalArgumentException("El correo ya está en uso por otro usuario");
            }

            // Validar si el teléfono ya está en uso por otro usuario
            Optional<User> existingByPhone = userPersistencePort.findByPhone(user.getPhone());
            if (existingByPhone.isPresent() && !existingByPhone.get().getId().equals(id)) {
                throw new IllegalArgumentException("El teléfono ya está en uso por otro usuario");
            }

            // Actualizar datos del usuario
            savedUser.setUsername(user.getUsername());
            savedUser.setPassword(user.getPassword());
            savedUser.setPhone(user.getPhone());

            return userPersistencePort.save(savedUser);
        }).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }

    @Override
    public void delete(Long id) {
        userPersistencePort.delete(id);
    }
}
