package com.engtechnologie.microcredit.features.order;

import com.engtechnologie.microcredit.features.customer.CustomerDto;
import com.engtechnologie.microcredit.reference.bank.BankDto;
import com.engtechnologie.microcredit.reference.lender.LenderDto;
import lombok.Builder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record OrderDto(Integer id,

                       String code,
                       Double amount,
                       String address,
                       String sectorActivity,
                       String operator,
                       Double salary,
                       Double income,
                       Double currentCredit,
                       Double propertyValue,
                       int duration,
                       ContratEnum contrat,
                       RemboursementEnum remboursement,
                       OrderTypeEnum typeOrder,
                       boolean includeInsurance,
                       OrderStatusEnum status,
                       ApplicationEnum application,
                       String description,
                       Instant orderDate,
                       CustomerDto customerDto,
                       BankDto bankDto,
                       Set<LenderDto> lendersDto) {

}