package com.engtechnologie.microcredit.reference.region;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/references/regions")
interface RegionApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody RegionDto regionDto);

    @GetMapping
    Collection<RegionDto> getAll();

    @GetMapping("/{id}")
    RegionDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody RegionDto regionDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);
}
