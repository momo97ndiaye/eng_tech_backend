package com.engtechnologie.microcredit.features.operation;

import com.engtechnologie.microcredit.features.loan.LoanDto;
import com.engtechnologie.microcredit.features.paymentloan.PaymentDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Builder
public
record OperationDto(Integer id, @NotBlank String name, String reference,
                    String description,
                    Double amount,

                    Double balance,
                    Instant operationDate,
                    OperationStatusEnum status,
                    OperationTypeEnum typeOperation,
                    LoanDto loan
                    // Set<PaymentDto> payments
) {

}