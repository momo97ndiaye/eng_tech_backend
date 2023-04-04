package com.engtechnologie.microcredit.reference.loantype;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/references/loan-types")
interface LoanTypeApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody LoanTypeDto loanTypeDto);

    @GetMapping
    Collection<LoanTypeDto> getAll();

    @GetMapping("/{loanTypeId}")
    LoanTypeDto getById(@PathVariable Integer loanTypeId);

    @PutMapping("/{loanTypeId}")
    ResponseEntity<Void> update(@PathVariable Integer loanTypeId, @Valid @RequestBody LoanTypeDto loanTypeDto);

    @DeleteMapping("/{loanTypeId}")
    ResponseEntity<Void> deleteById(@PathVariable Integer loanTypeId);
}
