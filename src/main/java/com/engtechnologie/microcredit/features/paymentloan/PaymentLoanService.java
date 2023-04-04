package com.engtechnologie.microcredit.features.paymentloan;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.features.loan.LoanDto;
import com.engtechnologie.microcredit.features.loan.LoanService;
import com.engtechnologie.microcredit.features.operation.OperationDto;
import com.engtechnologie.microcredit.features.operation.OperationMapper;
import com.engtechnologie.microcredit.features.operation.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PaymentLoanService {

    private final PaymentRepository repository;

    private final PaymentMapper mapper;

    private final LoanService loanService;

    private final OperationService operationService;

    private final OperationMapper operationMapper;

    public PaymentEntity create(PaymentDto PaymentDto) {

        var PaymentEntity = mapper.toEntity(PaymentDto);
        return repository.save(PaymentEntity);
    }

    public PaymentEntity generatePayment(OperationDto operationDto, Double amount, LoanDto loanDto, PaymentMethodEnum methodEnum)
    {

        // OperationEntity operationEntity =  operationMapper.toEntity(operationDto);

        // create payment
        PaymentDto paymentDto = PaymentDto.builder()
                .name(operationDto.name())
                .amount(amount)
                .loan(loanDto)
                // .operations(Set.of(operationDto))
                .paymentDate(Instant.now())
                .status(PaymentStatusEnum.PAID)
                .typePayment(PaymentTypeEnum.PAIEMENT)
                .methodPayment(methodEnum)
                .build();

        var paymentEntity = mapper.toEntity(paymentDto);
        // paymentEntity.addOperation(operationEntity);
        repository.save(paymentEntity);
        return paymentEntity;
    }

    Collection<PaymentDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    PaymentDto getById(Integer id) {
        return mapper.toDto(loadEntity(id));
    }

    PaymentEntity update(Integer id, PaymentDto PaymentDto) {

        var PaymentEntity = loadEntity(id);
        mapper.updateEntity(PaymentDto, PaymentEntity);
        return repository.save(PaymentEntity);
    }

    PaymentEntity deleteById(Integer id) {

        var PaymentEntity = loadEntity(id);
        return repository.save(PaymentEntity);
    }

    PaymentEntity changeStatus(Integer PaymentId, PaymentStatusEnum status) {
    	var PaymentEntity = loadEntity(PaymentId);
    	PaymentEntity.setStatus(status);
    	return repository.save(PaymentEntity);
    }

    private PaymentEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

    Collection<PaymentDto> getPaymentFromStatus(PaymentStatusEnum status) {
        return mapper.toDto(repository.findAllByStatus(status));
    }

    public Collection<PaymentDto> getPaymentsFromLoan(Integer loanId) {
        return mapper.toDto(repository.findAllByLoanId(loanId));
    }


    @Transactional
    public ResponseEntity<Void> createPayment(Integer loanId, Double amount, PaymentMethodEnum methodEnum) {

        try {
            //find loan by id
            LoanDto loanDto = loanService.getById(loanId);

            //check if sold is enough
            if (loanDto.balance() < amount) {
                return ResponseEntity.badRequest().build();
            }

            // find operation loan by id
            OperationDto operationDto = operationService.getFirstOpenOperation(loanId);

            // create payment
            PaymentEntity payment = generatePayment(operationDto, amount, loanDto, methodEnum);

            // operationsDto
            operationService.updateAfterPayment(operationDto.id(), amount, payment);

            if (operationDto.balance() < amount) {
                double  cashInHand = amount - operationDto.balance();
                updateNextOperation(loanId, loanDto, cashInHand, payment);
            }

            // update loan after payment
            loanService.updateAfterPayment(loanId, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    private void updateNextOperation(Integer loanId, LoanDto loanDto, double cashInHand, PaymentEntity payment) {
        OperationDto operationDto;
        // while cash in hand is not <=0 create payment and update cash in hand
        while (cashInHand > 0) {
            // find operation loan by id
            operationDto = operationService.getFirstOpenOperation(loanId);

            // operationsDto
            operationService.updateAfterPayment(operationDto.id(), cashInHand, payment);

            if (operationDto.balance() < cashInHand) {
                cashInHand = cashInHand - operationDto.balance();
            } else {
                cashInHand = 0;
            }
        }
    }

    @Transactional
    public ResponseEntity<Void> createPaymentWithCashInHand(Integer loanId) {
        return null;
    }
}
