package com.engtechnologie.microcredit.reference.bank;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public
record BankDto(Integer id, @NotBlank String code, @NotBlank String name, @NotBlank String location,
			   @NotBlank @Email String email, @NotNull ReferenceStatus status) {
}
