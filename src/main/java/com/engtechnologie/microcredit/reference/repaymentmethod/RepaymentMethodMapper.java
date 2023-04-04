package com.engtechnologie.microcredit.reference.repaymentmethod;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
interface RepaymentMethodMapper {

    RepaymentMethodDto toDto(RepaymentMethodEntity lenderEntity);

    Collection<RepaymentMethodDto> toDto(Collection<RepaymentMethodEntity> lenderEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    RepaymentMethodEntity toEntity(RepaymentMethodDto repaymentMethodDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(RepaymentMethodDto repaymentMethodDto, @MappingTarget RepaymentMethodEntity lenderEntity);
}
