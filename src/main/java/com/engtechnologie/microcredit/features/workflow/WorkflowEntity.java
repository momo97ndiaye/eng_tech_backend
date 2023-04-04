package com.engtechnologie.microcredit.features.workflow;

import com.engtechnologie.microcredit.features.order.OrderEntity;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order_workflow")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class WorkflowEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private WorkflowStepEnum step;

    @ManyToOne
    private OrderEntity order;

    private LocalDate stepDate;

    private String label;

    @Version
    private int version;
}
