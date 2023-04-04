package com.engtechnologie.microcredit.features.customer;

import com.engtechnologie.microcredit.features.order.OrderEntity;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class CustomerEntity extends AbstractAuditingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;
	private String name;
	private String surname;
	private Gender gender;
	private String email;

	@Column(name = "code", unique = true, updatable = false)
	String code;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Instant birthday;
	private String phoneNumber;
	private String job;
	private String jobDuration;
	private String activitySector;
	private String activityLocation;
	private CustomerStatusEnum status;
	private CustomerTypeEnum createdFrom;

	@OneToMany(mappedBy = "customer")
	private Set<OrderEntity> orders = new HashSet<>();

	@Version
	private int version;
}
