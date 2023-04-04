package com.engtechnologie.microcredit.features.conso;

import com.engtechnologie.microcredit.features.customer.CustomerEntity;
import com.engtechnologie.microcredit.reference.bank.BankEntity;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orderConso_customer")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class OrderConsoEntity extends AbstractAuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false, updatable = false)
  private Integer id;

  private Double amount;

  private String address;

  private String sectorActivity;

  private Instant orderConsoDate;

  @Enumerated(EnumType.STRING)
  private OrderConsoStatusEnum status;

  @ManyToOne
  private CustomerEntity customer;

  @Version
  private int version;
}
