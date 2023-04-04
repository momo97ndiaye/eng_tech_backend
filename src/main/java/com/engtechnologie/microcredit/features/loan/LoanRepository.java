package com.engtechnologie.microcredit.features.loan;

import com.engtechnologie.microcredit.features.paymentloan.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {

    // get loan by customer id
    @Query("SELECT l FROM LoanEntity l WHERE l.offer.order.customer.id = ?1")
    Collection<LoanEntity> findByCustomer(Integer customerId);
}