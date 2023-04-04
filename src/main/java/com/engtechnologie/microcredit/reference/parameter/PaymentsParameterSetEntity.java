package com.engtechnologie.microcredit.reference.parameter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "payments_parameter_set")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class PaymentsParameterSetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(nullable = false)
	private String payerId;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String payerPinCode;
}
