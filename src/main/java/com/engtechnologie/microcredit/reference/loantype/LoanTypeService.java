package com.engtechnologie.microcredit.reference.loantype;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
public class LoanTypeService {

    private final LoanTypeRepository repository;

    private final LoanTypeMapper mapper;


    LoanTypeEntity create(LoanTypeDto loanTypeDto) {

        var loanTypeEntity = mapper.toEntity(loanTypeDto);
        return repository.save(loanTypeEntity);
    }

    Collection<LoanTypeDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    LoanTypeDto getById(Integer loanTypeId) {
        return mapper.toDto(loadEntity(loanTypeId));
    }

    LoanTypeEntity update(Integer loanTypeId, LoanTypeDto loanTypeDto) {

        var loanTypeEntity = loadEntity(loanTypeId);
        mapper.updateEntity(loanTypeDto, loanTypeEntity);
        return repository.save(loanTypeEntity);
    }

    LoanTypeEntity deleteById(Integer loanTypeId) {

        var loanTypeEntity = loadEntity(loanTypeId);
        loanTypeEntity.setStatus(INACTIVE);
        return repository.save(loanTypeEntity);
    }

    private LoanTypeEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }
}
