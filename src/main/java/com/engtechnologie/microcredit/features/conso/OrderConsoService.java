package com.engtechnologie.microcredit.features.conso;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.features.customer.CustomerEntity;
import com.engtechnologie.microcredit.features.customer.CustomerService;
import com.engtechnologie.microcredit.features.offer.OfferEntity;
import com.engtechnologie.microcredit.features.offer.OfferMapper;
import com.engtechnologie.microcredit.features.offer.OfferService;
import com.engtechnologie.microcredit.features.offer.OfferStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
@RequiredArgsConstructor
class OrderConsoService {

    private final OrderConsoRepository repository;
    private final OrderConsoMapper mapper;
    private final OfferMapper offerMapper;
    private final CustomerService customerService;
    private final OfferService offerService;

    OrderConsoDto create(OrderConsoDto orderConsoDto) {

        var orderConsoEntity = mapper.toEntity(orderConsoDto);
        CustomerEntity customerEntity = customerService.findById(orderConsoDto.customerDto().id());
        orderConsoEntity.setCustomer(customerEntity);
        orderConsoEntity.setOrderConsoDate(Instant.now());
        OrderConsoEntity orderConso = repository.save(orderConsoEntity);

        return mapper.toDto(orderConso);
    }



    Collection<OrderConsoDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    OrderConsoDto getById(Integer orderConsoId) {
        return mapper.toDto(loadEntity(orderConsoId));
    }

    OrderConsoDto update(Integer id, OrderConsoDto orderConsoDto) {

        var orderConsoEntity = loadEntity(id);
        mapper.updateEntity(orderConsoDto, orderConsoEntity);
        return mapper.toDto(repository.save(orderConsoEntity));
    }

    OrderConsoEntity deleteById(Integer orderConsoId) {

        var DemandeEntity = loadEntity(orderConsoId);
        return repository.save(DemandeEntity);
    }

    OrderConsoEntity changeStatus(Integer orderConsoId, OrderConsoStatusEnum status) {
    	var DemandeEntity = loadEntity(orderConsoId);
    	DemandeEntity.setStatus(status);
    	return repository.save(DemandeEntity);
    }

    private OrderConsoEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

    Collection<OrderConsoDto> getDemandeFromStatus(OrderConsoStatusEnum status) {
        return mapper.toDto(repository.findAllByStatus(status));
    }

    // create Random OfferStatusEnum
    private OfferStatusEnum getRandomOfferStatus() {
    	OfferStatusEnum[] values = OfferStatusEnum.values();
    	int randomIndex = (int) (Math.random() * values.length);
    	return values[randomIndex];
    }

    // random 12 - 24 - 36
    private Integer getRandomDuration() {
    	Integer[] values = {12, 24, 36};
    	int randomIndex = (int) (Math.random() * values.length);
    	return values[randomIndex];
    }

    private Double getAmountPaid(Double montant_total, Integer nb_month, Double taux) {
        // creare payment monthly from amount and duration
        // calculate taux %
        Double taux_mensuel = taux / 100;
        System.out.println("taux mensuel " + taux_mensuel);
        Double calcul1 =  Math.pow(1 + taux_mensuel, nb_month);
        System.out.println("calcul1 " + calcul1);
        //  calcul1 round to nearest 50 or 100
        Double calcul2 = Math.pow(1+taux_mensuel, nb_month) - 1;
        System.out.println("calcul2 " + calcul2);
        //  calcul2 round to nearest 50 or 100
        Double montant_echeance = (montant_total * taux_mensuel *calcul1) / calcul2;
        //  montant_echeance round to nearest 50 or 100
        montant_echeance = roundToNearest(montant_echeance, 50.0);
        return montant_echeance;
    }

    // calculate date of payment
    private Instant calculateDateOfPayment(Date date, Integer nb_month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, nb_month);

        // convert  Date to LocalDate
        return cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toInstant();
    }

    // round 14672.897196261682 to 14670
    private Double roundToNearest(Double value, Double nearest) {
        return Math.round(value / nearest) * nearest;
    }
}
