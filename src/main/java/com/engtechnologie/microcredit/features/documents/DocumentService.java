package com.engtechnologie.microcredit.features.documents;

import com.engtechnologie.microcredit.exception.ResourceNotFoundException;
import com.engtechnologie.microcredit.features.documents.s3.DocumentS3Service;
import com.engtechnologie.microcredit.features.loan.LoanEntity;
import com.engtechnologie.microcredit.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DocumentService {

    @Value("${document.bucket-url}")
    private String bucketUrl;

    private final DocumentRepository repository;

    private final DocumentS3Service s3Service;

    private final DocumentMapper mapper;
    public DocumentDto create(DocumentDto documentDto, MultipartFile file) throws IOException {

        var documentEntity = mapper.toEntity(documentDto);
        // call service to create path document
        String tempFileName = s3Service.uploadDocument(file);
        documentEntity.setPath(bucketUrl + tempFileName);
        DocumentEntity document = repository.save(documentEntity);

        return mapper.toDto(document);
    }

    public void  deleteById(Integer id) {
        var documentEntity = loadEntity(id);
        s3Service.deleteDocument(documentEntity.getPath());
        repository.deleteById(id);
    }

    public DocumentDto update(Integer id, DocumentDto documentDto) {
        var documentEntity = loadEntity(id);
        mapper.updateEntity(documentDto, documentEntity);
        return mapper.toDto(repository.save(documentEntity));
    }

    public DocumentDto getById(Integer id) {
        var documentEntity = loadEntity(id);
        return mapper.toDto(repository.save(documentEntity));
    }

    public Collection<DocumentDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    private DocumentEntity loadEntity(Integer entityId) {
        return repository.findById(entityId).orElseThrow(ResourceNotFoundException::new);
    }

    public Collection<DocumentDto> getAllByOrder(Integer id) {

        return mapper.toDto(repository.findAllByOrderId(id));
    }
}
