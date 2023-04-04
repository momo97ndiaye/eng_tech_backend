package com.engtechnologie.microcredit.reference.bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/references/banks")
@CrossOrigin(maxAge = 3600)
interface BankApi {

    @PostMapping
    ResponseEntity<BankDto> create(@Valid @RequestBody BankDto bankDto);

    @GetMapping
    Collection<BankDto> getAll();

    @GetMapping("/{id}")
    BankDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody BankDto bankDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);
}
