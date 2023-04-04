package com.engtechnologie.microcredit.features.workflow;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface WorkflowRepository extends JpaRepository<WorkflowEntity, Integer> {
    Collection<WorkflowEntity> findAllByOrderId(Integer id);
}
