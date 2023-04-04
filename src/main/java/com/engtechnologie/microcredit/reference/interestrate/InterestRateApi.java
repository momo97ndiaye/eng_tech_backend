package com.engtechnologie.microcredit.reference.interestrate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/interest-rates")
interface InterestRateApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody InterestRateDto interestRateDto);

    @GetMapping
    Collection<InterestRateDto> getAll();

    @GetMapping("/{interestRateId}")
    InterestRateDto getById(@PathVariable Integer interestRateId);

    @PutMapping("/{interestRateId}")
    ResponseEntity<Void> update(@PathVariable Integer interestRateId, @Valid @RequestBody InterestRateDto interestRateDto);

    @DeleteMapping("/{interestRateId}")
    ResponseEntity<Void> deleteById(@PathVariable Integer interestRateId);
}
