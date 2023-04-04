package com.engtechnologie.microcredit.reference.repaymentmethod;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
class RepaymentMethodService {

    private final RepaymentMethodRepository repository;

    private final RepaymentMethodMapper mapper;


    RepaymentMethodEntity create(RepaymentMethodDto repaymentMethodDto) {

        var repaymentMethodEntity = mapper.toEntity(repaymentMethodDto);
        return repository.save(repaymentMethodEntity);
    }

    Collection<RepaymentMethodDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    RepaymentMethodDto getById(Integer repaymentMethodId) {
        return mapper.toDto(loadEntity(repaymentMethodId));
    }

    RepaymentMethodEntity update(Integer repaymentMethodId, RepaymentMethodDto repaymentMethodDto) {

        var repaymentMethodEntity = loadEntity(repaymentMethodId);
        mapper.updateEntity(repaymentMethodDto, repaymentMethodEntity);
        return repository.save(repaymentMethodEntity);
    }

    RepaymentMethodEntity deleteById(Integer repaymentMethodId) {

        var repaymentMethodEntity = loadEntity(repaymentMethodId);
        repaymentMethodEntity.setStatus(INACTIVE);
        return repository.save(repaymentMethodEntity);
    }

    private RepaymentMethodEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }
}
