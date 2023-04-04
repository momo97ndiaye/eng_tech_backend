package com.engtechnologie.microcredit.reference.interestrate;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
record InterestRateDto(Integer id, @NotBlank String code, @NotNull BigDecimal rate, String description, String tag,
                       String period, @NotNull ReferenceStatus status) {
}
