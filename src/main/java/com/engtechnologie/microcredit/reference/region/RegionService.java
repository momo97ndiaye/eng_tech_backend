package com.engtechnologie.microcredit.reference.region;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
class RegionService {

	private final RegionRepository repository;

	private final RegionMapper mapper;


	RegionEntity create(RegionDto regionDto) {

		var organizationEntity = mapper.toEntity(regionDto);
		return repository.save(organizationEntity);
	}

	Collection<RegionDto> getAll() {
		return mapper.toDto(repository.findAll());
	}

	RegionDto getById(Integer id) {
		return mapper.toDto(loadEntity(id));
	}

	RegionEntity update(Integer id, RegionDto regionDto) {

		var organizationEntity = loadEntity(id);
		mapper.updateEntity(regionDto, organizationEntity);
		return repository.save(organizationEntity);
	}

	RegionEntity deleteById(Integer id) {

		var organizationEntity = loadEntity(id);
		return repository.save(organizationEntity);
	}

	private RegionEntity loadEntity(Integer entityId) {
		return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
	}
}
