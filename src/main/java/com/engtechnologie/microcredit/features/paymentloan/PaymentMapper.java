package com.engtechnologie.microcredit.features.paymentloan;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
public
interface PaymentMapper {

    // @Mapping(target = "operations", source = "operations")
    PaymentDto toDto(PaymentEntity paymentEntity);

    Collection<PaymentDto> toDto(Collection<PaymentEntity> paymentEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    // @Mapping(target = "operations", source = "operations")
    PaymentEntity toEntity(PaymentDto paymentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    // @Mapping(target = "operations", source = "operations")
    void updateEntity(PaymentDto paymentDto, @MappingTarget PaymentEntity paymentEntity);
}
