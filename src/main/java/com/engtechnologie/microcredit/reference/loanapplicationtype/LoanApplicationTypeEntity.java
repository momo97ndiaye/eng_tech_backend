package com.engtechnologie.microcredit.reference.loanapplicationtype;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Indicates how the loan application was submitted (via Web, mobile, at a counter, ...)
 */
@Entity
@Table(name = "loan_application_type")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class LoanApplicationTypeEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReferenceStatus status;

    @Version
    private int version;
}
