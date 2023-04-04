package com.engtechnologie.microcredit.features.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class CustomerController implements CustomerApi {
    private final CustomerService service;
    @Override
    public ResponseEntity<CustomerDto> create(CustomerDto customerDto) {

        var resource = service.create(customerDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.id())
                .toUri();

        return ResponseEntity.created(resourceLocation).body(resource);
    }

    @Override
    public Collection<CustomerDto> getAll() {
        return service.getAll();
    }

    @Override
    public CustomerDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<CustomerDto> update(Integer id, CustomerDto customerDto) {
        var ressource = service.update(id, customerDto);
        return ResponseEntity.ok().body(ressource);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> changeStatus(Integer id, CustomerStatusEnum status) {
        service.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Collection<CustomerDto> getcustomerFromStatus(CustomerStatusEnum demandeStatus) {
    	return service.getcustomerFromStatus(demandeStatus);
    }

    @Override
    public CustomerDto getCustomerFromLoan(Integer id) {
        return service.getCustomerFromLoan(id);
    }
}
