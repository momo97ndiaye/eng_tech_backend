package com.engtechnologie.microcredit.payment;

import com.engtechnologie.microcredit.reference.parameter.ParameterService;
import com.engtechnologie.microcredit.reference.parameter.PaymentsParameterSetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
class PaymentService {

    private final ParameterService parameterService;
    private final OrangeMoneyClient orangeMoneyClient;

    void createPayment(PaymentDto paymentDto) {

        var parameterSet = parameterService.getPaymentsParameterSet();
        var cashInRequest = toCashInRequest(parameterSet, paymentDto);

        orangeMoneyClient.cashIn(cashInRequest);
    }

    public void createPayments(Collection<PaymentDto> paymentDtoCollection) {

        var parameterSet = parameterService.getPaymentsParameterSet();
        var bulkCashInRequest = paymentDtoCollection.stream().map(paymentDto -> toCashInRequest(parameterSet, paymentDto)).collect(toList());

        orangeMoneyClient.bulkCashIn(bulkCashInRequest, "http://localhost:8080/api/banks");
    }

    private OrangeMoneyClient.CashInRequest toCashInRequest(PaymentsParameterSetEntity paymentsParameterSet, PaymentDto paymentDto) {

        var partner = new OrangeMoneyClient.Partner("MSISDN", paymentsParameterSet.getPayerId(), paymentsParameterSet.getPayerPinCode());
        var customer = new OrangeMoneyClient.Customer("MSISDN", paymentDto.payee());
        var amount = new OrangeMoneyClient.Amount("XOF", paymentDto.amount());
        return new OrangeMoneyClient.CashInRequest(partner, customer, amount);
    }
}
