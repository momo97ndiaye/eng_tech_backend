package com.engtechnologie.microcredit.reference.interestrate;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
class InterestRateService {

	private final InterestRateRepository repository;

	private final InterestRateMapper mapper;


	InterestRateEntity create(InterestRateDto interestRateDto) {

		var interestRateEntity = mapper.toEntity(interestRateDto);
		return repository.save(interestRateEntity);
	}

	Collection<InterestRateDto> getAll() {
		return mapper.toDto(repository.findAll());
	}

	InterestRateDto getById(Integer interestRateId) {
		return mapper.toDto(loadEntity(interestRateId));
	}

	InterestRateEntity update(Integer interestRateId, InterestRateDto interestRateDto) {

		var interestRateEntity = loadEntity(interestRateId);
		mapper.updateEntity(interestRateDto, interestRateEntity);
		return repository.save(interestRateEntity);
	}

	InterestRateEntity deleteById(Integer interestRateId) {

		var interestRateEntity = loadEntity(interestRateId);
		interestRateEntity.setStatus(INACTIVE);
		return repository.save(interestRateEntity);
	}

	private InterestRateEntity loadEntity(Integer entityId) {
		return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
	}
}
