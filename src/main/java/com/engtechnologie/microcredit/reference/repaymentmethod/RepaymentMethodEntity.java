package com.engtechnologie.microcredit.reference.repaymentmethod;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "repayment_method")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class RepaymentMethodEntity extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	private String power;

	private LocalDate operationDate;

	private String operation;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ReferenceStatus status;

	@Version
	private int version;
}
