package com.engtechnologie.microcredit.features.operation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
public
interface OperationMapper {

    // @Mapping(target = "payments", source = "payments")
    OperationDto toDto(OperationEntity operationEntity);

    Collection<OperationDto> toDto(Collection<OperationEntity> operationEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    //@Mapping(target = "payments", source = "payments")
    OperationEntity toEntity(OperationDto operationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    // @Mapping(target = "payments", source = "payments")
    void updateEntity(OperationDto operationDto, @MappingTarget OperationEntity operationEntity);
}
