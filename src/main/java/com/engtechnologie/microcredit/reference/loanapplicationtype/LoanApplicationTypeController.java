package com.engtechnologie.microcredit.reference.loanapplicationtype;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class LoanApplicationTypeController implements LoanApplicationTypeApi {

    private final LoanApplicationTypeService service;

    @Override
    public ResponseEntity<Void> create(LoanApplicationTypeDto loanApplicationTypeDto) {

        var resource = service.create(loanApplicationTypeDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{loanApplicationTypeId}").build(resource.getId());

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<LoanApplicationTypeDto> getAll() {
        return service.getAll();
    }

    @Override
    public LoanApplicationTypeDto getById(Integer loanApplicationTypeId) {
        return service.getById(loanApplicationTypeId);
    }

    @Override
    public ResponseEntity<Void> update(Integer loanApplicationTypeId, LoanApplicationTypeDto loanApplicationTypeDto) {

        service.update(loanApplicationTypeId, loanApplicationTypeDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer loanApplicationTypeId) {

        service.deleteById(loanApplicationTypeId);
        return ResponseEntity.noContent().build();
    }
}
