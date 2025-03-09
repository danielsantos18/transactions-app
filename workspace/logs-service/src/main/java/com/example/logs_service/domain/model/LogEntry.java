package com.example.logs_service.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "logs")
public class LogEntry {

    @Id
    private String id;
    private String servicio;
    private String nivel; // INFO, WARN, ERROR
    private String mensaje;
    private LocalDateTime fecha;
    
    public LogEntry() {
        this.fecha = LocalDateTime.now();
    }

    public LogEntry(String servicio, String nivel, String mensaje) {
        this.servicio = servicio;
        this.nivel = nivel;
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
