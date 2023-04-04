package com.engtechnologie.microcredit.features.conso;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
interface OrderConsoMapper {

  @Mapping(target = "customerDto.id", source = "customer.id")
  OrderConsoDto toDto(OrderConsoEntity orderConsoEntity);

  Collection<OrderConsoDto> toDto(Collection<OrderConsoEntity> orderConsoEntities);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "customer.id", source = "customerDto.id")
  OrderConsoEntity toEntity(OrderConsoDto orderConsoDto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "orderConsoDto.customerId")
  @Mapping(target = "version", ignore = true)
  void updateEntity(OrderConsoDto orderConsoDto, @MappingTarget OrderConsoEntity orderConsoEntity);
}
