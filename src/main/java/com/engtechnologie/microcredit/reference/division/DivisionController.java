package com.engtechnologie.microcredit.reference.division;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class DivisionController implements DivisionApi {

    private final DivisionService service;

    @Override
    public ResponseEntity<Void> create(DivisionDto divisionDto) {

        var resource = service.create(divisionDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{divisionId}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<DivisionDto> getAll() {
        return service.getAll();
    }

    @Override
    public DivisionDto getById(Integer divisionId) {
        return service.getById(divisionId);
    }

    @Override
    public ResponseEntity<Void> update(Integer divisionId, DivisionDto divisionDto) {

        service.update(divisionId, divisionDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer divisionId) {

        service.deleteById(divisionId);
        return ResponseEntity.noContent().build();
    }
}
