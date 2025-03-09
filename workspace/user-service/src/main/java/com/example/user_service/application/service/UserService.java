package com.example.user_service.application.service;

import com.example.user_service.application.ports.input.UserServicePort;
import com.example.user_service.application.ports.output.UserPersistencePort;
import com.example.user_service.domain.exception.UserNotFoundException;
import com.example.user_service.domain.exception.ValidationException;
import com.example.user_service.domain.model.Role;
import com.example.user_service.domain.model.User;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private static final String NOTIFICATION_SERVICE_URL = "http://localhost:8082/notification/sent";
    private final RestTemplate restTemplate;
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

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        // Enviar mensaje de bienvenida
        String message = "¡Bienvenido, " + user.getUsername() + "! Gracias por registrarte.";

        // Crear un objeto para el cuerpo de la solicitud
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("phoneNumber", user.getPhone());
        requestBody.put("message", message);

        // Configurar los headers para indicar que el contenido es JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear la entidad HTTP con el cuerpo y los headers
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);

        // Llamar al microservicio de notificaciones
        try {
            String notificationResponse = restTemplate.postForObject(
                    NOTIFICATION_SERVICE_URL,
                    httpEntity,
                    String.class);
            System.out.println("Respuesta del servicio de notificaciones: " + notificationResponse);
        } catch (Exception e) {
            System.err.println("Error al enviar el mensaje de bienvenida: " + e.getMessage());
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
