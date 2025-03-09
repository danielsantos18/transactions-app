package com.example.logs_service.infrastructure.input.rest;

import com.example.logs_service.application.ports.input.LogServicePort;
import com.example.logs_service.domain.model.Log;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogServicePort logService;

    public LogController(LogServicePort logService) {
        this.logService = logService;
    }

    @PostMapping
    public Log createLog(@RequestBody Log log) {
        return logService.saveLog(log);
    }

    @GetMapping
    public List<Log> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/{id}")
    public Log getLogById(@PathVariable String id) {
        return logService.getLogById(id);
    }
}
