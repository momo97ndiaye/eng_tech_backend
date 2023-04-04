package com.engtechnologie.microcredit.features.documents;

import com.engtechnologie.microcredit.features.order.OrderDto;
import lombok.Builder;

@Builder
public record DocumentDto(String name,
                          String description,
                          String path,
                          DocumentTypeEnum type,
                          OrderDto orderDto) {
}
