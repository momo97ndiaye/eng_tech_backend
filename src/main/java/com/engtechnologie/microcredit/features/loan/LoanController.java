package com.engtechnologie.microcredit.features.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class LoanController implements LoanApi {

    private final LoanService service;

    @Override
    public ResponseEntity<LoanDto> create(LoanDto loanDto) {

        var resource = service.create(loanDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<LoanDto> getAll() {
        return service.getAll();
    }

    @Override
    public LoanDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<LoanDto> update(Integer id, LoanDto loanDto) {

        LoanDto loan = service.update(id, loanDto);
        return ResponseEntity.ok().body(loan);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> changeStatus(Integer id, LoanStatusEnum status) {
        service.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<String> open(Integer id) {
        LoanEntity loanEntity = service.updateStatutLoan(id, LoanStatusEnum.OPEN);
        if (loanEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("Prêt ouvert avec success! ");
    }

    @Override
    public ResponseEntity<String> close(Integer id) {
        LoanEntity loanEntity = service.updateStatutLoan(id, LoanStatusEnum.CLOSED);
        if (loanEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("Prêt cloturé avec success! ");
    }

    @Override
    public ResponseEntity<String> suspend(Integer id) {
        LoanEntity loanEntity = service.updateStatutLoan(id, LoanStatusEnum.SUSPEND);
        // return 404 if loan not found
        if (loanEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("Prêt suspendu avec success! ");
    }

    @Override
    public Collection<LoanDto> findByCustomer(Integer customerId) {
        return service.findByCustomer(customerId);
    }
}
