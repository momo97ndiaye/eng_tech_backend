package com.engtechnologie.microcredit.reference.borrowinggroup;

import com.engtechnologie.microcredit.enumeration.ReferenceStatus;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
record BorrowingGroupDto(Integer id, @NotBlank String code, @NotBlank String name, String tag, @Email String email,
                         @NotNull ReferenceStatus status) {
}
