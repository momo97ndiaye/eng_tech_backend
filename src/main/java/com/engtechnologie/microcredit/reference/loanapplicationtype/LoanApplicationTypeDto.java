package com.engtechnologie.microcredit.reference.loanapplicationtype;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
record LoanApplicationTypeDto(Integer id, @NotBlank String code, @NotBlank String name, @NotNull ReferenceStatus status) {
}
