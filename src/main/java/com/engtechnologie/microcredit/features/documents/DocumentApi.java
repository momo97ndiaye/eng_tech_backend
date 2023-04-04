package com.engtechnologie.microcredit.features.documents;

import com.engtechnologie.microcredit.features.loan.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

@RequestMapping("/api/documents")
@CrossOrigin(maxAge = 3600)
public interface DocumentApi {

    @PostMapping
    ResponseEntity<DocumentDto> create(@RequestParam(value = "order") Integer order, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description, @RequestParam(value = "file") MultipartFile file) throws IOException;

    @GetMapping
    Collection<DocumentDto> getAll();

    @GetMapping("/{id}")
    DocumentDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<DocumentDto> update(@PathVariable Integer id, @Valid @RequestBody DocumentDto documentDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @GetMapping("/order/{id}")
    Collection<DocumentDto> getAllByOrder(@PathVariable Integer id);

}
