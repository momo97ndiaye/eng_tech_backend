package com.engtechnologie.microcredit.reference.interestrate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class InterestRateController implements InterestRateApi {

    private final InterestRateService service;

    @Override
    public ResponseEntity<Void> create(InterestRateDto interestRateDto) {

        var resource = service.create(interestRateDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{interestRateId}").build(resource.getId());

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<InterestRateDto> getAll() {
        return service.getAll();
    }

    @Override
    public InterestRateDto getById(Integer interestRateId) {
        return service.getById(interestRateId);
    }

    @Override
    public ResponseEntity<Void> update(Integer interestRateId, InterestRateDto interestRateDto) {

        service.update(interestRateId, interestRateDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer interestRateId) {

        service.deleteById(interestRateId);
        return ResponseEntity.noContent().build();
    }
}
