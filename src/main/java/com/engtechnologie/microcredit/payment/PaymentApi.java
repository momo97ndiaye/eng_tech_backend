package com.engtechnologie.microcredit.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping("/api")
interface PaymentApi {

    @PostMapping("/payments")
    ResponseEntity<Void> createPayment(@RequestBody PaymentDto paymentDto);

    @PostMapping("/bulk-payments")
    ResponseEntity<Void> createPayments(@RequestBody Collection<PaymentDto> paymentDtoCollection);
}
