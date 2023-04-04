package com.engtechnologie.microcredit.features.loan;

import com.engtechnologie.microcredit.features.offer.OfferDto;
import com.engtechnologie.microcredit.features.operation.OperationDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

@Builder
public
record LoanDto(Integer id, String code, String name, String description, Double amount,
               Double fee,
               Double balance,
               Double cashInHand,
               int duration,
               Instant loanDate,

               Instant startDate,
               LoanStatusEnum status, OfferDto offerDto) {
}