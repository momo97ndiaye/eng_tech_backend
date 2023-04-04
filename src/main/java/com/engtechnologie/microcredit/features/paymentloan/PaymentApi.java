package com.engtechnologie.microcredit.features.paymentloan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/api/payments-loan")
@CrossOrigin(maxAge = 3600)
interface PaymentApi {

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody PaymentDto PaymentDto);

    @GetMapping
    Collection<PaymentDto> getAll();

    @GetMapping("/{id}")
    PaymentDto getById(@PathVariable Integer id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody PaymentDto PaymentDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Integer id);

    @PutMapping("/{id}/status")
    ResponseEntity<Void> changeStatus(@PathVariable Integer id, @RequestParam PaymentStatusEnum status);

    @GetMapping("/status/{paymentstatus}")
    Collection<PaymentDto> getPaymentFromStatus(@PathVariable PaymentStatusEnum paymentstatus);

    // get payments by loanId
    @GetMapping("/loan/{loanId}")
    Collection<PaymentDto> getPaymentsFromLoan(@PathVariable(name = "loanId", required = true) Integer loanId);

    @PostMapping("/payment/{loanId}")
    ResponseEntity<Void> createPayment(@PathVariable(name = "loanId", required = true) Integer loanId, @RequestParam("amount") Double amount, @RequestParam("type") PaymentMethodEnum methodEnum);

}
