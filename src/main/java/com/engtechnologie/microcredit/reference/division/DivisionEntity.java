package com.engtechnologie.microcredit.reference.division;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "division")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class DivisionEntity extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String email;

	private String phoneNumber;

	private BigDecimal budget;

	@Column(nullable = false)
	private BigDecimal interestRate;

	private short latenessGracePeriod;

	private short collectionGracePeriod;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ReferenceStatus status;

	@Version
	private int version;
}
