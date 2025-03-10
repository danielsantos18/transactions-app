package com.example.logs_service.infrastructure.output.persistence.mapper;

import com.example.logs_service.domain.model.Log;
import com.example.logs_service.infrastructure.output.persistence.entity.LogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogPersistenceMapper {
    LogPersistenceMapper INSTANCE = Mappers.getMapper(LogPersistenceMapper.class);

    LogEntity toEntity(Log log);
    Log toDomain(LogEntity entity);
}

