package com.example.logs_service.application.service;

import com.example.logs_service.application.ports.input.LogServicePort;
import com.example.logs_service.domain.model.Log;
import com.example.logs_service.infrastructure.input.rest.mapper.impl.LogRepositoryPort;


import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import java.util.List;

 
@Service   // Asegura que Spring reconozca esta clase como un bean
@RequiredArgsConstructor
public class LogService implements LogServicePort {

    private final LogRepositoryPort logRepository;



    @Override
    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    @Override
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    @Override
    public Log getLogById(String id) {
        return logRepository.findById(id).orElse(null);
    }
}
