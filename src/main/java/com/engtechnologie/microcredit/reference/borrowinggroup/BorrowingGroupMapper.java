package com.engtechnologie.microcredit.reference.borrowinggroup;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
interface BorrowingGroupMapper {

    BorrowingGroupDto toDto(BorrowingGroupEntity borrowingGroupEntity);

    Collection<BorrowingGroupDto> toDto(Collection<BorrowingGroupEntity> borrowingGroupEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    BorrowingGroupEntity toEntity(BorrowingGroupDto borrowingGroupDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(BorrowingGroupDto borrowingGroupDto, @MappingTarget BorrowingGroupEntity borrowingGroupEntity);
}
