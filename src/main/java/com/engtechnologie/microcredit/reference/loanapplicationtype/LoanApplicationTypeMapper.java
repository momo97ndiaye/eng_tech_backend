package com.engtechnologie.microcredit.reference.loanapplicationtype;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
interface LoanApplicationTypeMapper {

    LoanApplicationTypeDto toDto(LoanApplicationTypeEntity loanApplicationTypeEntity);

    Collection<LoanApplicationTypeDto> toDto(Collection<LoanApplicationTypeEntity> loanApplicationTypeEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    LoanApplicationTypeEntity toEntity(LoanApplicationTypeDto loanApplicationTypeDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(LoanApplicationTypeDto loanApplicationTypeDto, @MappingTarget LoanApplicationTypeEntity loanApplicationTypeEntity);
}
