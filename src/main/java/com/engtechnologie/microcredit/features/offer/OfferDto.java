package com.engtechnologie.microcredit.features.offer;

import com.engtechnologie.microcredit.features.order.OrderDto;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder
public
record OfferDto(Integer id, String code,

                  Double amount,
                  Double amountToPay,
                  OfferStatusEnum status,
                  String description,

                  Double fee,

                  int duration,

                  LocalDate offerDate,

                  LocalDate updatedStatutDate,

                  OrderDto orderDto
) {


}