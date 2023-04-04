package com.engtechnologie.microcredit.features.operation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {

    Collection<OperationEntity> findAllByStatus(OperationStatusEnum status);

    Collection<OperationEntity> findAllByLoanId(Integer loanId);

    Collection<OperationEntity> findOperationEntitiesByPaymentsId(Integer paymentId);

    OperationEntity findFirstByLoanIdAndStatusOrderByIdAsc(Integer loanId, OperationStatusEnum status);

}