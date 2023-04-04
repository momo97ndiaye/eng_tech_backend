package com.engtechnologie.microcredit.reference.operator;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class OperatorController implements OperatorApi {

    private final OperatorService service;

    @Override
    public ResponseEntity<Void> create(OperatorDto operatorDto) {

        var resource = service.create(operatorDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<OperatorDto> getAll() {
        return service.getAll();
    }

    @Override
    public OperatorDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, OperatorDto operatorDto) {

        service.update(id, operatorDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
