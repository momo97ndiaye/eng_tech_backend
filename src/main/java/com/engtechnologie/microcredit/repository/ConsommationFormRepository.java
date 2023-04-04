package com.engtechnologie.microcredit.repository;

import com.engtechnologie.microcredit.model.ConsommationFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsommationFormRepository extends JpaRepository<ConsommationFrom, Long> {

}
