package com.engtechnologie.microcredit.features.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class OfferController implements OfferApi {

    private final OfferService service;

    @Override
    public ResponseEntity<Void> create(OfferDto OfferDto) {

        var resource = service.create(OfferDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<OfferDto> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @Override
    public OfferDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, OfferDto OfferDto) {

        service.update(id, OfferDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> accepted(Integer id) {
        try {
            service.accepted(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Void> refused(Integer id) {
        try {
            service.refused(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Void> changeStatus(Integer id, String status) {
        try {
            service.changeStatus(id, OfferStatusEnum.valueOf(status));
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public Collection<OfferDto> getOfferFromStatus(String offerStatus) {
    	return service.getOfferFromStatus(OfferStatusEnum.valueOf(offerStatus));
    }

    @Override
    public String getOfferCalculation(Double amount, Integer duration, Double fee) {
        return service.getOfferCalculation(amount, duration, fee);
    }
}
