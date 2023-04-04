package com.engtechnologie.microcredit.reference.lender;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class LenderController implements LenderApi {

    private final LenderService service;

    @Override
    public ResponseEntity<Void> create(LenderDto lenderDto) {

        var resource = service.create(lenderDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{lenderId}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<LenderDto> getAll() {
        return service.getAll();
    }

    @Override
    public LenderDto getById(Integer lenderId) {
        return service.getById(lenderId);
    }

    @Override
    public ResponseEntity<Void> update(Integer lenderId, LenderDto lenderDto) {

        service.update(lenderId, lenderDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer lenderId) {

        service.deleteById(lenderId);
        return ResponseEntity.noContent().build();
    }
}
