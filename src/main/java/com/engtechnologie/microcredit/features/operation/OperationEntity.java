package com.engtechnologie.microcredit.features.operation;

import com.engtechnologie.microcredit.features.loan.LoanEntity;
import com.engtechnologie.microcredit.features.paymentloan.PaymentEntity;
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
@Table(name = "operation_loan")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class OperationEntity extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(nullable = false)
	private String name;

	private String reference;

	private String description;

	private Instant operationDate;

	private Double amount;

	private Double balance;

	private OperationStatusEnum status;

	private OperationTypeEnum typeOperation;

	private String method;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loan_id", nullable = false)
	private LoanEntity loan;
	@ManyToMany(cascade = {
			CascadeType.ALL})
	@JoinTable(name = "operation_payment",
			joinColumns = {@JoinColumn(name = "operation_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "payment_id", referencedColumnName = "id")})
	private Set<PaymentEntity> payments = new HashSet<>();;

	@Version
	private int version;


	public void addPayment(PaymentEntity payment) {
		if(this.payments == null) this.payments = new HashSet<>();
		this.payments.add(payment);
		Set<OperationEntity> operations =  payment.getOperations();
		if(operations == null) operations = new HashSet<>();
		operations.add(this);
	}
}
