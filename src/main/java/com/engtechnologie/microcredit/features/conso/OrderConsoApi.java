package com.engtechnologie.microcredit.features.conso;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/order-consos")
@CrossOrigin(maxAge = 3600)
interface OrderConsoApi {

    @PostMapping
    ResponseEntity<OrderConsoDto> create(@Valid @RequestBody OrderConsoDto orderConsoDto);

    @GetMapping
    Collection<OrderConsoDto> getAll();

    @GetMapping("/{id}")
    OrderConsoDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<OrderConsoDto> update(@PathVariable Integer id, @Valid @RequestBody OrderConsoDto orderConsoDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @PutMapping("/{id}/status")
    ResponseEntity<Void> changeStatus(@PathVariable Integer id, @RequestParam OrderConsoStatusEnum status);

    @GetMapping("/{status}")
    Collection<OrderConsoDto> getDemandeFromStatus(@PathVariable OrderConsoStatusEnum status);
}
