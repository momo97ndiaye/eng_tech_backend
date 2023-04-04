package com.engtechnologie.microcredit.reference.interestrate;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "interest_rate")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class InterestRateEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private BigDecimal rate;

    private String description;

    private String tag;

    private String period;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReferenceStatus status;

    @Version
    private int version;
}
