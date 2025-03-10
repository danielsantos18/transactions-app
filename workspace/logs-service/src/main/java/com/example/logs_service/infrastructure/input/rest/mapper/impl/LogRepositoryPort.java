package com.example.logs_service.infrastructure.input.rest.mapper.impl;
import com.example.logs_service.domain.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepositoryPort extends MongoRepository<Log, String> {
}