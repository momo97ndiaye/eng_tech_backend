package com.engtechnologie.microcredit.reference.division;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
class DivisionService {

	private final DivisionRepository repository;

	private final DivisionMapper mapper;


	DivisionEntity create(DivisionDto divisionDto) {

		var divisionEntity = mapper.toEntity(divisionDto);
		return repository.save(divisionEntity);
	}

	Collection<DivisionDto> getAll() {
		return mapper.toDto(repository.findAll());
	}

	DivisionDto getById(Integer divisionId) {
		return mapper.toDto(loadEntity(divisionId));
	}

	DivisionEntity update(Integer divisionId, DivisionDto divisionDto) {

		var divisionEntity = loadEntity(divisionId);
		mapper.updateEntity(divisionDto, divisionEntity);
		return repository.save(divisionEntity);
	}

	DivisionEntity deleteById(Integer divisionId) {

		var divisionEntity = loadEntity(divisionId);
		divisionEntity.setStatus(INACTIVE);
		return repository.save(divisionEntity);
	}

	private DivisionEntity loadEntity(Integer entityId) {
		return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
	}
}
