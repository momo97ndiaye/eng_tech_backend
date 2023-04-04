package com.engtechnologie.microcredit.reference.lender;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.reference.lender.LenderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.INACTIVE;

@Service
@RequiredArgsConstructor
public
class LenderService {

    private final LenderRepository repository;

    private final LenderMapper mapper;


    LenderEntity create(LenderDto lenderDto) {

        var lenderEntity = mapper.toEntity(lenderDto);
        return repository.save(lenderEntity);
    }

    Collection<LenderDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    LenderDto getById(Integer lenderId) {
        return mapper.toDto(loadEntity(lenderId));
    }

    LenderEntity update(Integer lenderId, LenderDto lenderDto) {

        var lenderEntity = loadEntity(lenderId);
        mapper.updateEntity(lenderDto, lenderEntity);
        return repository.save(lenderEntity);
    }

    LenderEntity deleteById(Integer lenderId) {

        var lenderEntity = loadEntity(lenderId);
        lenderEntity.setStatus(INACTIVE);
        return repository.save(lenderEntity);
    }

    private LenderEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

    public LenderEntity findById(Integer lenderId) {
        return repository.findById(lenderId).orElseThrow(ResourceNotFoundException::new);
    }
}
