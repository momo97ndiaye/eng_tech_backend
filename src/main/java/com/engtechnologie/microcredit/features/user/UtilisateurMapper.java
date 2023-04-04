package com.engtechnologie.microcredit.features.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;

@Mapper
public interface UtilisateurMapper {

  UtilisateurDto toDto(Utilisateur utilisateur);

  Collection<UtilisateurDto> toDto(Collection<Utilisateur> utilisateurs);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "version", ignore = true)
  Utilisateur toEntity(UtilisateurDto utilisateurDto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "version", ignore = true)
  void updateEntity(UtilisateurDto utilisateurDto, @MappingTarget Utilisateur utilisateur);
}
