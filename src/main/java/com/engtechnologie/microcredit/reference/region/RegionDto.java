package com.engtechnologie.microcredit.reference.region;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
record RegionDto(Integer id, @NotBlank String code, @NotBlank String name) {
}
