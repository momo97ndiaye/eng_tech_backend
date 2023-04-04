package com.engtechnologie.microcredit.features.order;

import com.engtechnologie.microcredit.features.operation.OperationEntity;
import com.engtechnologie.microcredit.reference.lender.LenderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {


    Page<OrderEntity> findAll(Pageable pageable);

    Page<OrderEntity> findAllByStatus(OrderStatusEnum status, Pageable pageable);

    Collection<OrderEntity> findAllByStatus(OrderStatusEnum status);

    Collection<OrderEntity> findAllByCustomerId(Integer customerId);

    Collection<LenderEntity> findOrderEntitiesByLendersId(Integer lenderId);

}