package com.engtechnologie.microcredit.features.documents;

import com.engtechnologie.microcredit.features.customer.CustomerEntity;
import com.engtechnologie.microcredit.features.order.OrderEntity;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "documents")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
@Builder
public class DocumentEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(unique = true)
    private String path;

    @Enumerated(EnumType.STRING)
    private DocumentTypeEnum type;

    @ManyToOne
    private OrderEntity order;

    @Version
    private int version;
}
