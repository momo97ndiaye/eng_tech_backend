package com.engtechnologie.microcredit.reference.loantype;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
record LoanTypeDto(Integer id, @NotBlank String name, String power,
                   LocalDate operationDate, String operation, @NotNull ReferenceStatus status) {
}
