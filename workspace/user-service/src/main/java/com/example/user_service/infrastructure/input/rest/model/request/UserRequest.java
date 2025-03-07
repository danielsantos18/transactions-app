package com.example.user_service.infrastructure.input.rest.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    @NotNull(message = "El campo no puede ser vacio")
    private String username;
    @NotNull(message = "El campo no puede ser vacio")
    private String email;
    @NotNull(message = "El campo no puede ser vacio")
    private String password;
    @NotNull(message = "El campo no puede ser vacio")
    private String phone;
}
