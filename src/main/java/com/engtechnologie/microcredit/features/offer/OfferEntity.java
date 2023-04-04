package com.engtechnologie.microcredit.features.offer;

import com.engtechnologie.microcredit.features.order.OrderEntity;
import com.engtechnologie.microcredit.model.Institution;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "offer")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
@Builder
public class OfferEntity extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(name = "code", unique = true, updatable = false)
	String code;

	private Double amount;

	private Double amountToPay;

	private Double fee;

	private Integer duration;

	private String description;

	private LocalDate offerDate;

	private LocalDate updatedStatutDate;

	@Enumerated(EnumType.STRING)
	private OfferStatusEnum status;

	@OneToOne
	@JoinColumn(name = "order_customer_id", referencedColumnName = "id")
	private OrderEntity order;

	@OneToOne
	@JoinColumn(name = "institution_id", referencedColumnName = "id")
	private Institution institution;

	@Version
	private int version;
}
