package com.engtechnologie.microcredit.features.documents;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
    Collection<DocumentEntity> findAllByOrderId(Integer id);
}
