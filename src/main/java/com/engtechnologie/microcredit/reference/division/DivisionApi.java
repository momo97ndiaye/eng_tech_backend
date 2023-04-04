package com.engtechnologie.microcredit.reference.division;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/divisions")
@CrossOrigin(maxAge = 3600)
interface DivisionApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody DivisionDto divisionDto);

    @GetMapping
    Collection<DivisionDto> getAll();

    @GetMapping("/{divisionId}")
    DivisionDto getById(@PathVariable Integer divisionId);

    @PutMapping("/{divisionId}")
    ResponseEntity<Void> update(@PathVariable Integer divisionId, @Valid @RequestBody DivisionDto divisionDto);

    @DeleteMapping("/{divisionId}")
    ResponseEntity<Void> deleteById(@PathVariable Integer divisionId);
}
