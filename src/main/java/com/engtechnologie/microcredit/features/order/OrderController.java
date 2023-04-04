package com.engtechnologie.microcredit.features.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class OrderController implements OrderApi {
    private final OrderService service;
    @Override
    public ResponseEntity<OrderDto> create(OrderDto orderDto) {

        var resource = service.create(orderDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{orderId}")
                .buildAndExpand(resource.id())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<OrderDto> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @Override
    public OrderDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<OrderDto> update(Integer id, OrderDto demandeDto) {

        var response = service.update(id, demandeDto);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> changeStatus(Integer id, OrderStatusEnum status) {
        service.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> reject(Integer id) {
        service.changeStatus(id, OrderStatusEnum.REFUSED);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Collection<OrderDto> getDemandeFromStatus(OrderStatusEnum demandeStatus) {
    	return service.getDemandeFromStatus(demandeStatus);
    }

    @Override
    public Collection<OrderDto> getDemandeFromCustomer(Integer id) {
        return service.getDemandeFromCustomer(id);
    }
}
