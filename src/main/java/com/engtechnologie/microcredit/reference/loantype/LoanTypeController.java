package com.engtechnologie.microcredit.reference.loantype;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class LoanTypeController implements LoanTypeApi {

    private final LoanTypeService service;

    @Override
    public ResponseEntity<Void> create(LoanTypeDto loanTypeDto) {

        var resource = service.create(loanTypeDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{loanTypeId}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<LoanTypeDto> getAll() {
        return service.getAll();
    }

    @Override
    public LoanTypeDto getById(Integer loanTypeId) {
        return service.getById(loanTypeId);
    }

    @Override
    public ResponseEntity<Void> update(Integer loanTypeId, LoanTypeDto loanTypeDto) {

        service.update(loanTypeId, loanTypeDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer loanTypeId) {

        service.deleteById(loanTypeId);
        return ResponseEntity.noContent().build();
    }
}
