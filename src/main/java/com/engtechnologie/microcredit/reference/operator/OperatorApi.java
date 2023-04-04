package com.engtechnologie.microcredit.reference.operator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/references/operators")
interface OperatorApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody OperatorDto operatorDto);

    @GetMapping
    Collection<OperatorDto> getAll();

    @GetMapping("/{id}")
    OperatorDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody OperatorDto operatorDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);
}
