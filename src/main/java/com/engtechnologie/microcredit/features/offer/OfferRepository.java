package com.engtechnologie.microcredit.features.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OfferRepository extends JpaRepository<OfferEntity, Integer> {

    Page<OfferEntity>findAll(Pageable pageable);
    Collection<OfferEntity> findAllByStatus(OfferStatusEnum status);
}