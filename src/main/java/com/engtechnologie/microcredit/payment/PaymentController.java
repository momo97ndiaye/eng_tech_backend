package com.engtechnologie.microcredit.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
class PaymentController implements PaymentApi {

    private final PaymentService service;

    @Override
    public ResponseEntity<Void> createPayment(PaymentDto paymentDto) {
        service.createPayment(paymentDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> createPayments(Collection<PaymentDto> paymentDtoCollection) {
        service.createPayments(paymentDtoCollection);
        return ResponseEntity.ok().build();
    }
}
