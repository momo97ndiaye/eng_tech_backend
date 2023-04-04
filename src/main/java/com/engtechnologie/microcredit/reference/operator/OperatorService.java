package com.engtechnologie.microcredit.reference.operator;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
class OperatorService {

	private final OperatorRepository repository;

	private final OperatorMapper mapper;


	OperatorEntity create(OperatorDto operatorDto) {

		var organizationEntity = mapper.toEntity(operatorDto);
		return repository.save(organizationEntity);
	}

	Collection<OperatorDto> getAll() {
		return mapper.toDto(repository.findAll());
	}

	OperatorDto getById(Integer id) {
		return mapper.toDto(loadEntity(id));
	}

	OperatorEntity update(Integer id, OperatorDto operatorDto) {

		var organizationEntity = loadEntity(id);
		mapper.updateEntity(operatorDto, organizationEntity);
		return repository.save(organizationEntity);
	}

	OperatorEntity deleteById(Integer id) {

		var organizationEntity = loadEntity(id);
		return repository.save(organizationEntity);
	}

	private OperatorEntity loadEntity(Integer entityId) {
		return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
	}
}
