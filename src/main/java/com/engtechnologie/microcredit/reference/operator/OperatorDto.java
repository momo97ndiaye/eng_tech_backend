package com.engtechnologie.microcredit.reference.operator;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
record OperatorDto(Integer id, @NotBlank String code, @NotBlank String name,
                    String adress, String phone, String email) {
}
