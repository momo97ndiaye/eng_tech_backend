package com.engtechnologie.microcredit.reference.borrowinggroup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/borrowing-groups")
interface BorrowingGroupApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody BorrowingGroupDto borrowingGroupDto);

    @GetMapping
    Collection<BorrowingGroupDto> getAll();

    @GetMapping("/{borrowingGroupId}")
    BorrowingGroupDto getById(@PathVariable Integer borrowingGroupId);

    @PutMapping("/{borrowingGroupId}")
    ResponseEntity<Void> update(@PathVariable Integer borrowingGroupId, @Valid @RequestBody BorrowingGroupDto borrowingGroupDto);

    @DeleteMapping("/{borrowingGroupId}")
    ResponseEntity<Void> deleteById(@PathVariable Integer borrowingGroupId);
}
