package com.engtechnologie.microcredit.reference.repaymentmethod;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/repayment-methods")
interface RepaymentMethodApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody RepaymentMethodDto repaymentMethodDto);

    @GetMapping
    Collection<RepaymentMethodDto> getAll();

    @GetMapping("/{repaymentMethodId}")
    RepaymentMethodDto getById(@PathVariable Integer repaymentMethodId);

    @PutMapping("/{repaymentMethodId}")
    ResponseEntity<Void> update(@PathVariable Integer repaymentMethodId, @Valid @RequestBody RepaymentMethodDto repaymentMethodDto);

    @DeleteMapping("/{repaymentMethodId}")
    ResponseEntity<Void> deleteById(@PathVariable Integer repaymentMethodId);
}
