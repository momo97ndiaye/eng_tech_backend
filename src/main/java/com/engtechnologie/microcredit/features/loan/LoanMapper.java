package com.engtechnologie.microcredit.features.loan;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
public
interface LoanMapper {

    @Mapping(target = "offerDto.id", source = "offer.id")
    @Mapping(target = "offerDto.orderDto.id", source = "offer.order.id")
    @Mapping(target = "offerDto.orderDto.customerDto.id", source = "offer.order.customer.id")
    LoanDto toDto(LoanEntity loanEntity);

    Collection<LoanDto> toDto(Collection<LoanEntity> loanEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "offer.id", source = "offerDto.id")
    LoanEntity toEntity(LoanDto LoanDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(LoanDto loanDto, @MappingTarget LoanEntity loanEntity);
}
