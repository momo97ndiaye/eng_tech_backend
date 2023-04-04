package com.engtechnologie.microcredit.reference.lender;

import com.engtechnologie.microcredit.features.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LenderRepository extends JpaRepository<LenderEntity, Integer> {

    Collection<OrderEntity> findLenderEntitiesByOrdersId(Integer orderId);


}