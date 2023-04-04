package com.engtechnologie.microcredit.reference.loanapplicationtype;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
public class LoanApplicationTypeService {

    private final LoanApplicationTypeRepository repository;

    private final LoanApplicationTypeMapper mapper;


    LoanApplicationTypeEntity create(LoanApplicationTypeDto loanApplicationTypeDto) {

        var loanApplicationTypeEntity = mapper.toEntity(loanApplicationTypeDto);
        return repository.save(loanApplicationTypeEntity);
    }

    Collection<LoanApplicationTypeDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    LoanApplicationTypeDto getById(Integer loanApplicationTypeId) {
        return mapper.toDto(loadEntity(loanApplicationTypeId));
    }

    LoanApplicationTypeEntity update(Integer loanApplicationTypeId, LoanApplicationTypeDto loanApplicationTypeDto) {

        var loanApplicationTypeEntity = loadEntity(loanApplicationTypeId);
        mapper.updateEntity(loanApplicationTypeDto, loanApplicationTypeEntity);
        return repository.save(loanApplicationTypeEntity);
    }

    LoanApplicationTypeEntity deleteById(Integer loanApplicationTypeId) {

        var loanApplicationTypeEntity = loadEntity(loanApplicationTypeId);
        loanApplicationTypeEntity.setStatus(INACTIVE);
        return repository.save(loanApplicationTypeEntity);
    }

    private LoanApplicationTypeEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }
}
