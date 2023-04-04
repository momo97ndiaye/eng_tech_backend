package com.engtechnologie.microcredit.reference.region;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class RegionController implements RegionApi {

    private final RegionService service;

    @Override
    public ResponseEntity<Void> create(RegionDto regionDto) {

        var resource = service.create(regionDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<RegionDto> getAll() {
        return service.getAll();
    }

    @Override
    public RegionDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, RegionDto regionDto) {

        service.update(id, regionDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
