package com.engtechnologie.microcredit.features.paymentloan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

    Collection<PaymentEntity> findAllByStatus(PaymentStatusEnum status);
    Collection<PaymentEntity> findAllByLoanId(Integer loanId);

    Collection<PaymentEntity> findPaymentEntitiesByOperationsId(Integer operationId);
}