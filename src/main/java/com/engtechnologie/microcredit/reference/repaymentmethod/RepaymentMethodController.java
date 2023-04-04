package com.engtechnologie.microcredit.reference.repaymentmethod;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class RepaymentMethodController implements RepaymentMethodApi {

    private final RepaymentMethodService service;

    @Override
    public ResponseEntity<Void> create(RepaymentMethodDto repaymentMethodDto) {

        var resource = service.create(repaymentMethodDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{repaymentMethodId}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<RepaymentMethodDto> getAll() {
        return service.getAll();
    }

    @Override
    public RepaymentMethodDto getById(Integer repaymentMethodId) {
        return service.getById(repaymentMethodId);
    }

    @Override
    public ResponseEntity<Void> update(Integer repaymentMethodId, RepaymentMethodDto repaymentMethodDto) {

        service.update(repaymentMethodId, repaymentMethodDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer repaymentMethodId) {

        service.deleteById(repaymentMethodId);
        return ResponseEntity.noContent().build();
    }
}
