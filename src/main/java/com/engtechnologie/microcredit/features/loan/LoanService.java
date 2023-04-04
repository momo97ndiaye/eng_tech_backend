package com.engtechnologie.microcredit.features.loan;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.features.operation.OperationDto;
import com.engtechnologie.microcredit.features.operation.OperationService;
import com.engtechnologie.microcredit.features.operation.OperationStatusEnum;
import com.engtechnologie.microcredit.features.operation.OperationTypeEnum;
import com.engtechnologie.microcredit.utils.CodeGenerator;
import com.engtechnologie.microcredit.utils.LoanCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository repository;

    private final LoanMapper mapper;

    private final OperationService operationService;


    public LoanEntity create(LoanDto LoanDto) {

        var loanEntity = mapper.toEntity(LoanDto);
        loanEntity.setCode(CodeGenerator.generateCode("LON-"));
        LoanEntity loan = repository.save(loanEntity);
        //createPayment
        createBills(loan);
        return loan;
    }

    Collection<LoanDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    public LoanDto getById(Integer LoanId) {
        return mapper.toDto(loadEntity(LoanId));
    }

    LoanDto update(Integer LoanId, LoanDto LoanDto) {

        var loanEntity = loadEntity(LoanId);
        mapper.updateEntity(LoanDto, loanEntity);
        return mapper.toDto(repository.save(loanEntity));
    }

    LoanEntity deleteById(Integer LoanId) {

        var LoanEntity = loadEntity(LoanId);
        return repository.save(LoanEntity);
    }

    LoanEntity changeStatus(Integer LoanId, LoanStatusEnum status) {
        var LoanEntity = loadEntity(LoanId);
        LoanEntity.setStatus(status);
        return repository.save(LoanEntity);
    }

    private LoanEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

    private void createBills(LoanEntity loanEntity) {

        Double amountPaid = LoanCalculator.getAmountPaid(loanEntity.getAmount(), loanEntity.getDuration(), loanEntity.getFee());

        // create payment from loan
        for (int i = 1; i <= loanEntity.getDuration(); i++) {
            Date date = Date.from(loanEntity.getLoanDate().atZone(ZoneId.systemDefault()).toInstant());
            OperationDto operationDto = OperationDto.builder()
                    .amount(amountPaid)
                    .balance(amountPaid)
                    .reference("REF-"+loanEntity.getCode() +"-"+ i)
                    .operationDate(calculateDateOfPayment(date, i))
                    .name("ECH_00"+loanEntity.getCode() +"-" + i)
                    .description("Echéance numéro!  " + i)
                    .loan(mapper.toDto(loanEntity))
                    .typeOperation(OperationTypeEnum.PAIEMENT)
                    .status(OperationStatusEnum.OPEN)
                    .build();
            operationService.create(operationDto);
        }
    }

    // calculate date of payment
    private Instant calculateDateOfPayment(Date date, Integer nb_month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, nb_month);

        // convert  Date to LocalDate
        return cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toInstant();
    }

    public Collection<LoanDto> findByCustomer(Integer customerId) {
        return mapper.toDto(repository.findByCustomer(customerId));
    }

    public LoanEntity updateStatutLoan(Integer loanId, LoanStatusEnum status) {
         LoanEntity loanEntity = loadEntity(loanId);
        //Throw exeption if loan is not existe
        if (loanEntity == null) {
            throw new ResourceNotFoundException();
        }
        loanEntity.setStatus(status);
        loanEntity.setStartDate(Instant.now());
        return repository.save(loanEntity);
    }

    public void updateAfterPayment(Integer loanId, Double amount) {
        LoanEntity loanEntity = loadEntity(loanId);
        //Throw exeption if loan is not existe
        if (loanEntity == null) {
            throw new ResourceNotFoundException();
        }

        Double newBalance = Math.max(loanEntity.getBalance() - amount, 0.0);
        loanEntity.setBalance(newBalance);

        repository.save(loanEntity);
    }
}
