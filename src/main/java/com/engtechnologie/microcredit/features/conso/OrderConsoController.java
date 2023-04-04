package com.engtechnologie.microcredit.features.conso;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class OrderConsoController implements OrderConsoApi {
    private final OrderConsoService service;
    @Override
    public ResponseEntity<OrderConsoDto> create(OrderConsoDto orderConsoDto) {

        var resource = service.create(orderConsoDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{orderConsoId}")
                .buildAndExpand(resource.id())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<OrderConsoDto> getAll() {
        return service.getAll();
    }

    @Override
    public OrderConsoDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<OrderConsoDto> update(Integer id, OrderConsoDto orderConsoDto) {

        var response = service.update(id, orderConsoDto);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> changeStatus(Integer id, OrderConsoStatusEnum status) {
        service.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Collection<OrderConsoDto> getDemandeFromStatus(OrderConsoStatusEnum demandeStatus) {
    	return service.getDemandeFromStatus(demandeStatus);
    }
}
