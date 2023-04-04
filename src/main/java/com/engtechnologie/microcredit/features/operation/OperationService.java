package com.engtechnologie.microcredit.features.operation;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.features.paymentloan.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository repository;

    private final OperationMapper mapper;


    public OperationEntity create(OperationDto OperationDto) {

        var OperationEntity = mapper.toEntity(OperationDto);
        return repository.save(OperationEntity);
    }

    Collection<OperationDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    OperationDto getById(Integer id) {
        return mapper.toDto(loadEntity(id));
    }

    public OperationEntity update(Integer OperationId, OperationDto OperationDto) {

        var OperationEntity = loadEntity(OperationId);
        mapper.updateEntity(OperationDto, OperationEntity);
        return repository.save(OperationEntity);
    }

    OperationEntity deleteById(Integer OperationId) {

        var OperationEntity = loadEntity(OperationId);
        return repository.save(OperationEntity);
    }

    OperationEntity changeStatus(Integer OperationId, OperationStatusEnum status) {
    	var OperationEntity = loadEntity(OperationId);
    	OperationEntity.setStatus(status);
    	return repository.save(OperationEntity);
    }

    private OperationEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

    Collection<OperationDto> getOperationFromStatus(OperationStatusEnum status) {
        return mapper.toDto(repository.findAllByStatus(status));
    }

    public Collection<OperationDto> getOperationsFromLoan(Integer loanId) {
        return mapper.toDto(repository.findAllByLoanId(loanId));
    }

    public OperationDto getFirstOpenOperation(Integer loanId) {
        OperationStatusEnum status = OperationStatusEnum.OPEN;
        return mapper.toDto(repository.findFirstByLoanIdAndStatusOrderByIdAsc(loanId, status));
    }

    public void updateAfterPayment(Integer id, Double amount, PaymentEntity paymentEntity) {
        OperationEntity operation = repository.findById(id).orElseThrow(ResourceNotFoundException::new);

        if(operation.getBalance().doubleValue() == amount || operation.getBalance() < amount){
            operation.setStatus(OperationStatusEnum.CLOSED);
            operation.setBalance(0.0);
        } else{
            operation.setBalance(operation.getBalance() - amount);
            operation.setStatus(OperationStatusEnum.OPEN);
        }
        operation.addPayment(paymentEntity);
        repository.save(operation);
    }
}
