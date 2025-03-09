package com.example.logs_service.infrastructure.output.persistence;


import com.example.logs_service.application.ports.ouput.LogPersistencePort;
import com.example.logs_service.domain.model.Log;
import com.example.logs_service.infrastructure.output.persistence.entity.LogEntity;
import com.example.logs_service.infrastructure.output.persistence.mapper.LogPersistenceMapper;
import com.example.logs_service.infrastructure.output.persistence.repository.LogRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogPersistenceAdapter implements LogPersistencePort {

    private final LogRepository logRepository;
    private final LogPersistenceMapper logPersistenceMapper;

    public LogPersistenceAdapter(LogRepository logRepository, LogPersistenceMapper logPersistenceMapper) {
        this.logRepository = logRepository;
        this.logPersistenceMapper = logPersistenceMapper;
    }

    @Override
    public Log saveLog(Log log) {
        LogEntity entity = logPersistenceMapper.toEntity(log);
        return logPersistenceMapper.toDomain(logRepository.save(entity));
    }

    @Override
    public List<Log> getAllLogs() {
        return logRepository.findAll()
                .stream()
                .map(logPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Log getLogById(String id) {
        return logRepository.findById(id)
                .map(logPersistenceMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Log> getLogsByDateRange(LocalDateTime inicio, LocalDateTime fin) {
        return logRepository.findByFechaBetween(inicio, fin)
                .stream()
                .map(logPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> getLogsByService(String servicio) {
        return logRepository.findByServicio(servicio)
                .stream()
                .map(logPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Log> getLogsByLevel(String nivel) {
        return logRepository.findByNivel(nivel)
                .stream()
                .map(logPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}