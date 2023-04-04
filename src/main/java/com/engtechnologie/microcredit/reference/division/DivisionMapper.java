package com.engtechnologie.microcredit.reference.division;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
interface DivisionMapper {

    DivisionDto toDto(DivisionEntity divisionEntity);

    Collection<DivisionDto> toDto(Collection<DivisionEntity> divisionEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    DivisionEntity toEntity(DivisionDto divisionDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(DivisionDto divisionDto, @MappingTarget DivisionEntity divisionEntity);
}
