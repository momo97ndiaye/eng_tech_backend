package com.engtechnologie.microcredit.features.operation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class OperationController implements OperationApi {

    private final OperationService service;

    @Override
    public ResponseEntity<Void> create(OperationDto OperationDto) {

        var resource = service.create(OperationDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<OperationDto> getAll() {
        return service.getAll();
    }

    @Override
    public OperationDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, OperationDto OperationDto) {

        service.update(id, OperationDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> changeStatus(Integer id, OperationStatusEnum status) {
        service.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Collection<OperationDto> getOperationFromStatus(OperationStatusEnum status) {
    	return service.getOperationFromStatus(status);
    }

    @Override
    public Collection<OperationDto> getOperationsFromLoan(Integer id) {
        return service.getOperationsFromLoan(id);
    }
}
