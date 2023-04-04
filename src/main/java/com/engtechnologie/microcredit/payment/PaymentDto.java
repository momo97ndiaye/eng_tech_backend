package com.engtechnologie.microcredit.payment;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
record PaymentDto(String payee, BigDecimal amount) {
}
