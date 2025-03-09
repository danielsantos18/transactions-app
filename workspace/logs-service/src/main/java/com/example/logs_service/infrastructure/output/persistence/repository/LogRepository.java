package com.example.logs_service.infrastructure.output.persistence.repository;

import com.example.logs_service.infrastructure.output.persistence.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<LogEntity, String> {
    List<LogEntity> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    List<LogEntity> findByServicio(String servicio);
    List<LogEntity> findByNivel(String nivel);
}
