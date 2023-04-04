package com.engtechnologie.microcredit.repository;

import com.engtechnologie.microcredit.model.ClientForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientFormRepository extends JpaRepository<ClientForm, Long> {
}
