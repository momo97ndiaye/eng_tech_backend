package com.engtechnologie.microcredit.reference.operator;

import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "operators")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class OperatorEntity extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String phone;

	private String email;

	private String adress;

	@Version
	private int version;
}
