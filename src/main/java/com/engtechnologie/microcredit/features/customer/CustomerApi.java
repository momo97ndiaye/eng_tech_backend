package com.engtechnologie.microcredit.features.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/customers")
@CrossOrigin(maxAge = 3600)
interface CustomerApi {

    @PostMapping
    ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto customerDto);

    @GetMapping
    Collection<com.engtechnologie.microcredit.features.customer.CustomerDto> getAll();

    @GetMapping("/{id}")
    CustomerDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<CustomerDto> update(@PathVariable Integer id, @Valid @RequestBody CustomerDto customerDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @PutMapping("/{id}/status")
    ResponseEntity<Void> changeStatus(@PathVariable Integer id, @RequestParam CustomerStatusEnum status);

    @GetMapping("/statut/{status}")
    Collection<CustomerDto> getcustomerFromStatus(@PathVariable CustomerStatusEnum status);

    @GetMapping("/loan/{id}")
    CustomerDto getCustomerFromLoan(@PathVariable(name = "id", required = true) Integer id);
}
