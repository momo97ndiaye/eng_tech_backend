package com.engtechnologie.microcredit.reference.lender;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import com.engtechnologie.microcredit.features.order.OrderEntity;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lender")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class LenderEntity extends AbstractAuditingEntity {

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

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ReferenceStatus status;

	@ManyToMany(cascade = {
			CascadeType.ALL
	},
			mappedBy = "lenders")
	private Set<OrderEntity> orders = new HashSet<>();

	@Version
	private int version;

}
