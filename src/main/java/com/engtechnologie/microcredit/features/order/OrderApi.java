package com.engtechnologie.microcredit.features.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/orders")
@CrossOrigin(maxAge = 3600)
interface OrderApi {

    @PostMapping
    ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto demandeDto);

    @GetMapping
    Collection<OrderDto> getAll(@PageableDefault(size = 10, sort = "orderDate") Pageable pageable);

    @GetMapping("/{id}")
    OrderDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<OrderDto> update(@PathVariable Integer id, @Valid @RequestBody OrderDto demandeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @PutMapping("/{id}/status")
    ResponseEntity<Void> changeStatus(@PathVariable Integer id, @RequestParam OrderStatusEnum status);

    @PutMapping("/{id}/refused")
    ResponseEntity<Void> reject(@PathVariable Integer id);

    @GetMapping("/status")
    Collection<OrderDto> getDemandeFromStatus(@RequestParam(name = "status") OrderStatusEnum status);

    @GetMapping("/customer/{id}")
    Collection<OrderDto> getDemandeFromCustomer(@PathVariable Integer id);
}
