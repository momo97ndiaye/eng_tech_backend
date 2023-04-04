package com.engtechnologie.microcredit.features.customer;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;

@Builder
public
record CustomerDto(Integer id, String code, @NotBlank String name,
                   @NotBlank String surname,
                   @NotBlank String email,
                   @NotBlank String phoneNumber,
                   String gender,
                   Instant birthday,
                   String job,
                   String jobDuration,
                   String activitySector,
                   String activityLocation,
                   CustomerStatusEnum status,
                   CustomerTypeEnum createdFrom
                 ) {

}