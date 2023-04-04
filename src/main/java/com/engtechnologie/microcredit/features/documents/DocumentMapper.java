package com.engtechnologie.microcredit.features.documents;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
public
interface DocumentMapper {

    @Mapping(target = "orderDto.id", source = "order.id")
    DocumentDto toDto(DocumentEntity documentEntity);

    Collection<DocumentDto> toDto(Collection<DocumentEntity> documentEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "order.id", source = "orderDto.id")
    DocumentEntity toEntity(DocumentDto documentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "order.id", source = "orderDto.id")
    void updateEntity(DocumentDto documentDto, @MappingTarget DocumentEntity documentEntity);
}
