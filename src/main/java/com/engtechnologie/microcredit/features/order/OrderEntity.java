package com.engtechnologie.microcredit.features.order;

import com.engtechnologie.microcredit.features.customer.CustomerEntity;
import com.engtechnologie.microcredit.features.documents.DocumentEntity;
import com.engtechnologie.microcredit.features.operation.OperationEntity;
import com.engtechnologie.microcredit.reference.bank.BankEntity;
import com.engtechnologie.microcredit.reference.lender.LenderEntity;
import com.engtechnologie.microcredit.shared.AbstractAuditingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_customer")
@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
public class OrderEntity extends AbstractAuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false, updatable = false)
  private Integer id;

    @Column(name = "code", unique = true, nullable = false)
    String code;

  private Double amount;

  private String address;

  private String operator;

  private String description;

  private Instant orderDate;

  private Double salary;

  private Double currentCredit;

  private Double income;

  private Double propertyValue;

  private int duration;

  private ContratEnum contrat;

  private RemboursementEnum remboursement;

  private OrderTypeEnum typeOrder;

  private boolean includeInsurance;

  private String sectorActivity;

  @Enumerated(EnumType.STRING)
  private OrderStatusEnum status;

  @Enumerated(EnumType.STRING)
  private ApplicationEnum application;

  @ManyToOne
  private CustomerEntity customer;

  @OneToOne
  private BankEntity bank;

  @OneToMany(mappedBy = "order")
  private Set<DocumentEntity> documents = new HashSet<>();

  @ManyToMany(cascade = {
          CascadeType.ALL})
  @JoinTable(name = "order_lender",
          joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
          inverseJoinColumns = {@JoinColumn(name = "lender_id", referencedColumnName = "id")})
  private Set<LenderEntity> lenders = new HashSet<>();

  @Version
  private int version;

  // method format orderDate to String DD-MM-YYYY
    public String getOrderDateFormat() {
        return Instant.ofEpochMilli(orderDate.toEpochMilli())
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    //method format amount to String 000.000.000 CFA
    public String getAmountFormat() {
        return String.format("%,d", amount.intValue()).replace(',', '.') + " CFA";
    }

    public String getPropertyValueFormat() {
        return String.format("%,d", propertyValue.intValue()).replace(',', '.') + " CFA";
    }


    public void addLender(LenderEntity lender) {
      if(this.lenders == null) this.lenders = new HashSet<>();
      this.lenders.add(lender);
      Set<OrderEntity> orders =  lender.getOrders();
      if(orders == null) orders = new HashSet<>();
      orders.add(this);
    }


}
