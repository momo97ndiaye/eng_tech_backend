package com.engtechnologie.microcredit.features.operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/operations")
@CrossOrigin(maxAge = 3600)
interface OperationApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody OperationDto OperationDto);

    @GetMapping
    Collection<OperationDto> getAll();

    @GetMapping("/{id}")
    OperationDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody OperationDto OperationDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @PutMapping("/{id}/status")
    ResponseEntity<Void> changeStatus(@PathVariable Integer id, @RequestParam OperationStatusEnum status);

    @GetMapping("/status/{status}")
    Collection<OperationDto> getOperationFromStatus(@PathVariable OperationStatusEnum status);

    // get operations by loanId
    @GetMapping("/loan/{id}")
    Collection<OperationDto> getOperationsFromLoan(@PathVariable(name = "id", required = true) Integer id);
}
