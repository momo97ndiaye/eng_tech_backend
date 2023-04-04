package com.engtechnologie.microcredit.features.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
interface OrderMapper {

  @Mapping(target = "customerDto.id", source = "customer.id")
  @Mapping(target = "bankDto.id", source = "bank.id")
  OrderDto toDto(OrderEntity orderEntity);

  Collection<OrderDto> toDto(Collection<OrderEntity> orderEntities);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "customer.id", source = "customerDto.id")
  @Mapping(target = "bank.id", source = "bankDto.id")
  OrderEntity toEntity(OrderDto orderDto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "orderDto.customerId")
  @Mapping(target = "orderDto.bankId")
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "customer.id", source = "customerDto.id")
  @Mapping(target = "bank.id", source = "bankDto.id")
  void updateEntity(OrderDto orderDto, @MappingTarget OrderEntity orderEntity);
}
