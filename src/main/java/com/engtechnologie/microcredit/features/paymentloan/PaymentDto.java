package com.engtechnologie.microcredit.features.paymentloan;

import com.engtechnologie.microcredit.features.loan.LoanDto;
import com.engtechnologie.microcredit.features.operation.OperationDto;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Set;

@Builder
public
record PaymentDto(Integer id, @NotBlank String name, String description, Double amount,

                  Instant paymentDate,
                  PaymentStatusEnum status,
                  PaymentTypeEnum typePayment,

                  PaymentMethodEnum methodPayment,
                  LoanDto loan
                  //Set<OperationDto> operations
                ) {
}