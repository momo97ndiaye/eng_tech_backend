package com.engtechnologie.microcredit.features.conso;

import com.engtechnologie.microcredit.features.customer.CustomerDto;
import lombok.Builder;

import java.time.Instant;

@Builder
public record OrderConsoDto(Integer id,
                Double amount,
                String address,
                String sectorActivity,
                OrderConsoStatusEnum status,
                Instant orderConsoDate,
                CustomerDto customerDto) {

}