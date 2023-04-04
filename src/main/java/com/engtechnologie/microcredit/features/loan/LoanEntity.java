package com.engtechnologie.microcredit.features.loan;

import com.engtechnologie.microcredit.features.offer.OfferEntity;
import com.engtechnologie.microcredit.features.operation.OperationDto;
import com.engtechnologie.microcredit.features.operation.OperationEntity;
import com.engtechnologie.microcredit.model.Institution;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "loan")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class LoanEntity extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(name = "code", unique = true, updatable = false)
	String code;

	@Column(nullable = false)
	private String name;

	private String description;

	private Instant loanDate;

	private Instant startDate;

	private Double amount;

	private Double amountPaid;

	private Double fee;

	private int duration;

	private Double balance;

	@Column(columnDefinition = "double default 0")
	private Double cashInHand;

	@Enumerated(EnumType.STRING)
	private LoanStatusEnum status ;

	@OneToOne
	@JoinColumn(name = "offer_id", referencedColumnName = "id")
	private OfferEntity offer;

	@Version
	private int version;
}
