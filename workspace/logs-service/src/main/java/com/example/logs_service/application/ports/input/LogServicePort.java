package com.example.logs_service.application.ports.input;

import com.example.logs_service.domain.model.Log;
import java.util.List;

public interface LogServicePort {
    Log saveLog(Log log);
    List<Log> getAllLogs();
    Log getLogById(String id);
}
