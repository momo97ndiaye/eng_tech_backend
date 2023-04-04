package com.engtechnologie.microcredit.features.paymentloan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class PaymentLoanController implements PaymentApi {

    private final PaymentLoanService service;

    @Override
    public ResponseEntity<Void> create(PaymentDto PaymentDto) {

        var resource = service.create(PaymentDto);
        var resourceLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    @Override
    public Collection<PaymentDto> getAll() {
        return service.getAll();
    }

    @Override
    public PaymentDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<Void> update(Integer id, PaymentDto PaymentDto) {

        service.update(id, PaymentDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> changeStatus(Integer id, PaymentStatusEnum status) {
        service.changeStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Collection<PaymentDto> getPaymentFromStatus(PaymentStatusEnum paymentStatus) {
    	return service.getPaymentFromStatus(paymentStatus);
    }

    @Override
    public Collection<PaymentDto> getPaymentsFromLoan(Integer loanId) {
        return service.getPaymentsFromLoan(loanId);
    }

    @Override
    public ResponseEntity<Void> createPayment(Integer loanId, Double amount, PaymentMethodEnum methodEnum) {
        return service.createPayment(loanId, amount, methodEnum);
    }
}
