package com.engtechnologie.microcredit.reference.division;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
record DivisionDto(Integer id, @NotBlank String code, @NotBlank String name, @NotBlank String location,
                   @NotBlank String email, String phoneNumber, BigDecimal budget, @NotNull BigDecimal interestRate,
                   @NotNull ReferenceStatus status, @NotNull Short latenessGracePeriod, @NotNull Short collectionGracePeriod) {
}
