package com.engtechnologie.microcredit.features.conso;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderConsoRepository extends JpaRepository<OrderConsoEntity, Integer> {

    Collection<OrderConsoEntity> findAllByStatus(OrderConsoStatusEnum status);
}