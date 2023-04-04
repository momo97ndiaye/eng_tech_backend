package com.engtechnologie.microcredit.reference.parameter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParameterService {

    private final PaymentsParameterSetRepository paymentsParameterSetRepository;

    public PaymentsParameterSetEntity getPaymentsParameterSet() {
        return paymentsParameterSetRepository.findFirstByOrderByIdDesc();
    }
}
