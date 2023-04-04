package com.engtechnologie.microcredit.reference.lender;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/references/lenders")
interface LenderApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody LenderDto lenderDto);

    @GetMapping
    Collection<LenderDto> getAll();

    @GetMapping("/{lenderId}")
    LenderDto getById(@PathVariable Integer lenderId);

    @PutMapping("/{lenderId}")
    ResponseEntity<Void> update(@PathVariable Integer lenderId, @Valid @RequestBody LenderDto lenderDto);

    @DeleteMapping("/{lenderId}")
    ResponseEntity<Void> deleteById(@PathVariable Integer lenderId);
}
