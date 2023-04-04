package com.engtechnologie.microcredit.reference.bank;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="bank")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class BankEntity extends AbstractAuditingEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Column(unique = true)
	private String code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ReferenceStatus status;

	@Version
	private int version;
}
