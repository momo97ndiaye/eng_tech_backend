package com.engtechnologie.microcredit.reference.parameter;

import org.springframework.data.jpa.repository.JpaRepository;

interface PaymentsParameterSetRepository extends JpaRepository<PaymentsParameterSetEntity, Integer> {

    PaymentsParameterSetEntity findFirstByOrderByIdDesc();
}