package com.engtechnologie.microcredit.reference.bank;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class BankController implements BankApi {

    private final BankService service;

    @Override
    public ResponseEntity<BankDto> create(BankDto bankDto) {

        var resource = service.create(bankDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.id())
                .toUri();

        return ResponseEntity.created(resourceLocation).body(resource);
    }

    @Override
    public Collection<BankDto> getAll() {
        return service.getAll();
    }

    @Override
    public BankDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, BankDto bankDto) {

        var resource = service.update(id, bankDto);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
