package com.engtechnologie.microcredit.features.offer;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/offers")
@CrossOrigin(maxAge = 3600)
interface OfferApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody OfferDto OfferDto);

    @GetMapping
    Collection<OfferDto> getAll(Pageable pageable);

    @GetMapping("/{id}")
    OfferDto getById(@PathVariable(value = "id", required = true) Integer offerId);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody OfferDto OfferDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @PutMapping("/{id}/status")
    ResponseEntity<Void> changeStatus(@PathVariable(value = "id", required = true) Integer id, @RequestParam String status);

    @PutMapping("/accepted/{id}")
    ResponseEntity<Void> accepted(@PathVariable(value = "id", required = true) Integer id);

    @PutMapping("/refused/{id}")
    ResponseEntity<Void> refused(@PathVariable(value = "id", required = true) Integer id);


    @GetMapping("/status/{offerStatus}")
    Collection<OfferDto> getOfferFromStatus(@PathVariable String OfferStatus);

    @GetMapping("/calculation")
    String getOfferCalculation(@RequestParam Double amount, @RequestParam Integer duration, @RequestParam Double fee);

}
