package com.engtechnologie.microcredit.features.paymentloan;

import com.engtechnologie.microcredit.features.loan.LoanEntity;
import com.engtechnologie.microcredit.features.operation.OperationEntity;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payment_loan")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class PaymentEntity extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(nullable = false)
	private String name;

	private String description;

	private Instant paymentDate;

	private Double amount;

	private PaymentStatusEnum status;

	private PaymentTypeEnum typePayment;

	private PaymentMethodEnum methodPayment;

	private String method;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loan_id", nullable = false)
	private LoanEntity loan;

	@ManyToMany(cascade = {
					CascadeType.ALL
			},
			mappedBy = "payments")
	private Set<OperationEntity> operations = new HashSet<>();;


	@Version
	private int version;

	public void addOperation(OperationEntity operationEntity) {
		if (operations == null) {
		operations = new HashSet<>();
		}
		operations.add(operationEntity);
		operationEntity.getPayments().add(this);
	}
}
