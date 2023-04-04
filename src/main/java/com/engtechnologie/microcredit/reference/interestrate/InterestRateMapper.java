package com.engtechnologie.microcredit.reference.interestrate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
interface InterestRateMapper {

    InterestRateDto toDto(InterestRateEntity interestRateEntity);

    Collection<InterestRateDto> toDto(Collection<InterestRateEntity> interestRateEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    InterestRateEntity toEntity(InterestRateDto interestRateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(InterestRateDto interestRateDto, @MappingTarget InterestRateEntity interestRateEntity);
}
