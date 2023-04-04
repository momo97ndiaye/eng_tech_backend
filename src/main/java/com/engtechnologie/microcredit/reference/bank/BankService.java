package com.engtechnologie.microcredit.reference.bank;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
class BankService {

	private final BankRepository repository;

	private final BankMapper mapper;


	BankDto create(BankDto bankDto) {

		var bankEntity = mapper.toEntity(bankDto);
		bankEntity.setCode(CodeGenerator.generateCode("BANK-"));
		repository.save(bankEntity);
		return mapper.toDto(bankEntity);
	}

	Collection<BankDto> getAll() {
		return mapper.toDto(repository.findAll());
	}

	BankDto getById(Integer bankId) {
		return mapper.toDto(loadEntity(bankId));
	}

	BankDto update(Integer bankId, BankDto bankDto) {

		var bankEntity = loadEntity(bankId);
		mapper.updateEntity(bankDto, bankEntity);

		if(bankEntity.getCode() == null) {
			bankEntity.setCode(CodeGenerator.generateCode("BANK-"));
		}

		bankEntity = repository.save(bankEntity);

		return mapper.toDto(bankEntity);
	}

	BankEntity deleteById(Integer bankId) {

		var bankEntity = loadEntity(bankId);
		bankEntity.setStatus(INACTIVE);
		return repository.save(bankEntity);
	}

	private BankEntity loadEntity(Integer entityId) {
		return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
	}
}
