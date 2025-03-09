package com.example.logs_service.application.ports.ouput;

import com.example.logs_service.domain.model.Log;
import java.time.LocalDateTime;
import java.util.List;

public interface LogPersistencePort {
    Log saveLog(Log log);
    List<Log> getAllLogs();
    Log getLogById(String id);
    
    //  Agregar los métodos faltantes:
    List<Log> getLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Log> getLogsByService(String service);
    List<Log> getLogsByLevel(String level);
}
