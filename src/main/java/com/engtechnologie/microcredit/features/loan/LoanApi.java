package com.engtechnologie.microcredit.features.loan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/loans")
@CrossOrigin(maxAge = 3600)
interface LoanApi {

    @PostMapping
    ResponseEntity<LoanDto> create(@Valid @RequestBody LoanDto LoanDto);

    @GetMapping
    Collection<LoanDto> getAll();

    @GetMapping("/{id}")
    LoanDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<LoanDto> update(@PathVariable Integer id, @Valid @RequestBody LoanDto LoanDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @PutMapping("/{id}/status")
    ResponseEntity<Void> changeStatus(@PathVariable Integer id, @RequestParam LoanStatusEnum status);

    @PutMapping("/open/{id}")
    ResponseEntity<String> open(@PathVariable Integer id);

    @PutMapping("/close/{id}")
    ResponseEntity<String> close(@PathVariable Integer id);

    @PutMapping("/suspend/{id}")
    ResponseEntity<String> suspend(@PathVariable Integer id);

    @GetMapping("/loan/{customerId}")
    Collection<LoanDto> findByCustomer(@PathVariable Integer customerId);
    }
