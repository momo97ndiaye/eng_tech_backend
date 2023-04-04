package com.engtechnologie.microcredit.features.offer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
public
interface OfferMapper {

    @Mapping(target = "orderDto.id", source = "order.id")
    @Mapping(target = "orderDto.customerDto.id", source = "order.customer.id")
    OfferDto toDto(OfferEntity offerEntity);

    Collection<OfferDto> toDto(Collection<OfferEntity> offerEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "order.id", source = "orderDto.id")
    //@Mapping(target = "order.customer.id", source = "orderDto.customerDto.id")
    OfferEntity toEntity(OfferDto offerDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(OfferDto offerDto, @MappingTarget OfferEntity offerEntity);
}
