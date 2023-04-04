package com.engtechnologie.microcredit.features.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    Collection<CustomerEntity> findAllByStatus(CustomerStatusEnum status);

    @Query("SELECT c FROM CustomerEntity c WHERE c.id IN (SELECT l.offer.order.customer.id FROM LoanEntity l WHERE l.id = ?1)")
    CustomerEntity getCustomerFromLoan(Integer id);
}