package com.engtechnologie.microcredit.reference.borrowinggroup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class BorrowingGroupController implements BorrowingGroupApi {

    private final BorrowingGroupService service;

    @Override
    public ResponseEntity<Void> create(BorrowingGroupDto borrowingGroupDto) {

        var resource = service.create(borrowingGroupDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{borrowingGroupId}").build(resource.getId());

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<BorrowingGroupDto> getAll() {
        return service.getAll();
    }

    @Override
    public BorrowingGroupDto getById(Integer borrowingGroupId) {
        return service.getById(borrowingGroupId);
    }

    @Override
    public ResponseEntity<Void> update(Integer borrowingGroupId, BorrowingGroupDto borrowingGroupDto) {

        service.update(borrowingGroupId, borrowingGroupDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer borrowingGroupId) {

        service.deleteById(borrowingGroupId);
        return ResponseEntity.noContent().build();
    }
}
