package com.engtechnologie.microcredit.features.offer;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.features.loan.LoanEntity;
import com.engtechnologie.microcredit.features.loan.LoanMapper;
import com.engtechnologie.microcredit.features.loan.LoanService;
import com.engtechnologie.microcredit.features.loan.LoanStatusEnum;
import com.engtechnologie.microcredit.features.order.OrderService;
import com.engtechnologie.microcredit.features.order.OrderStatusEnum;
import com.engtechnologie.microcredit.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository repository;

    private final OfferMapper mapper;

    private final LoanMapper loanMapper;

    private final LoanService loanService;

    private final OrderService orderService;

    public OfferEntity create(OfferDto offerDto) {

        var offerEntity = mapper.toEntity(offerDto);
        offerEntity.setCode(CodeGenerator.generateCode("OFF-"));
        offerEntity.setOfferDate(LocalDate.now());
        offerEntity.setStatus(OfferStatusEnum.CREATED);

        // change order status to OFFERED when offer is created
        orderService.changeStatus(offerDto.orderDto().id(), OrderStatusEnum.OFFERED);

        return repository.save(offerEntity);
    }

    Collection<OfferDto> getAll(Pageable pageable) {
        Page<OfferEntity> offerEntityPage = repository.findAll(pageable);
        return mapper.toDto(offerEntityPage.toList());
    }

    OfferDto getById(Integer OfferId) {
        return mapper.toDto(loadEntity(OfferId));
    }

    OfferEntity update(Integer OfferId, OfferDto OfferDto) {

        var OfferEntity = loadEntity(OfferId);
        mapper.updateEntity(OfferDto, OfferEntity);
        return repository.save(OfferEntity);
    }

    void deleteById(Integer OfferId) {

        var OfferEntity = loadEntity(OfferId);
        repository.save(OfferEntity);
    }

    public void refused(Integer id) {
        var offerEntity = loadEntity(id);

        if(!offerEntity.getStatus().equals(OfferStatusEnum.CREATED)) {
            throw new IllegalArgumentException("Offer status must be CREATED");
        }

        if(offerEntity.getStatus().equals(OfferStatusEnum.REFUSED)) {
            throw new IllegalArgumentException("Offer is already refused");
        }

        offerEntity.setStatus(OfferStatusEnum.REFUSED);
        offerEntity.setUpdatedStatutDate(LocalDate.now());
        OfferEntity offer = repository.save(offerEntity);

        orderService.changeStatus(offer.getOrder().getId(), OrderStatusEnum.REFUSED);

    }

    @Transactional
    public void accepted(Integer id) {
        var offerEntity = loadEntity(id);

        if(!offerEntity.getStatus().equals(OfferStatusEnum.CREATED)) {
            throw new IllegalArgumentException("Offer status must be CREATED");
        }

        if(offerEntity.getStatus().equals(OfferStatusEnum.ACCEPTED)) {
            throw new IllegalArgumentException("Offer is already refused");
        }

        offerEntity.setStatus(OfferStatusEnum.ACCEPTED);
        offerEntity.setUpdatedStatutDate(LocalDate.now());
        OfferEntity offer = repository.save(offerEntity);

        // create Loan
        createLoan(offer);

        // update order status
        orderService.changeStatus(offer.getOrder().getId(), OrderStatusEnum.ACCEPTED);

    }

    @Transactional
    void changeStatus(Integer id, OfferStatusEnum status) {
    	var offerEntity = loadEntity(id);
        offerEntity.setStatus(status);
        offerEntity.setUpdatedStatutDate(LocalDate.now());
        repository.save(offerEntity);

        // switch OfferStatusEnum
        switch (status) {
        case ACCEPTED:

            break;
        case REFUSED:
                // find order
            	break;
        }


    }



    private void createLoan(OfferEntity offer) {
        double balance = roundToNearest(offer.getAmount() + offer.getAmount() * (offer.getFee()/100));
        LoanEntity loanEntity = LoanEntity.builder()
                .name(offer.getDescription())
                .amount(offer.getAmount())
                .fee(offer.getFee())
                .offer(offer)
                .description(offer.getDescription())
                .status(LoanStatusEnum.PENDING)
                .balance(balance)
                .loanDate(Instant.now())
                .duration(offer.getDuration())
                .build();

        loanService.create(loanMapper.toDto(loanEntity));
    }

    private OfferEntity loadEntity(Integer id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    Collection<OfferDto> getOfferFromStatus(OfferStatusEnum status) {
        return mapper.toDto(repository.findAllByStatus(status));
    }

    private Double getAmountPaid(Double montant_total, Integer nb_month, Double taux) {
        // creare payment monthly from amount and duration
        // calculate taux %
        Double taux_mensuel = taux / 100;
        System.out.println("taux mensuel " + taux_mensuel);
        Double calcul1 =  Math.pow(1 + taux_mensuel, nb_month);
        System.out.println("calcul1 " + calcul1);
        //  calcul1 round to nearest 50 or 100
        double calcul2 = Math.pow(1+taux_mensuel, nb_month) - 1;
        System.out.println("calcul2 " + calcul2);
        //  calcul2 round to nearest 50 or 100
        Double montant_echeance = (montant_total * taux_mensuel *calcul1) / calcul2;
        //  montant_echeance round to nearest 50 or 100
        montant_echeance = roundToNearest(montant_echeance);
        return montant_echeance;
    }

    // round 14672.897196261682 to 14670
    private Double roundToNearest(Double value) {
        return Math.round(value / (Double) 10.0) * 10.0;
    }


    public String getOfferCalculation(Double amount, Integer duration, Double fee) {
        Double amountToPay = getAmountPaid(amount, duration, fee);
        return amountToPay.toString();
    }
}
