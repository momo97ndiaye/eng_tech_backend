package com.engtechnologie.microcredit.reference.loanapplicationtype;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/loan-application-types")
interface LoanApplicationTypeApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody LoanApplicationTypeDto loanApplicationTypeDto);

    @GetMapping
    Collection<LoanApplicationTypeDto> getAll();

    @GetMapping("/{loanApplicationTypeId}")
    LoanApplicationTypeDto getById(@PathVariable Integer loanApplicationTypeId);

    @PutMapping("/{loanApplicationTypeId}")
    ResponseEntity<Void> update(@PathVariable Integer loanApplicationTypeId, @Valid @RequestBody LoanApplicationTypeDto loanApplicationTypeDto);

    @DeleteMapping("/{loanApplicationTypeId}")
    ResponseEntity<Void> deleteById(@PathVariable Integer loanApplicationTypeId);
}
