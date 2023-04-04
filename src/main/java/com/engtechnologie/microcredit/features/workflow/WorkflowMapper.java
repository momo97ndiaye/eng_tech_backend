package com.engtechnologie.microcredit.features.workflow;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
public interface WorkflowMapper {

    @Mapping(target = "orderDto.id", source = "order.id")
    WorkflowDto toDto(WorkflowEntity WorkflowEntity);

    Collection<WorkflowDto> toDto(Collection<WorkflowEntity> WorkflowEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "order.id", source = "orderDto.id")
    WorkflowEntity toEntity(WorkflowDto workflowDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "order.id", source = "orderDto.id")
    void updateEntity(WorkflowDto workflowDto, @MappingTarget WorkflowEntity WorkflowEntity);
}
