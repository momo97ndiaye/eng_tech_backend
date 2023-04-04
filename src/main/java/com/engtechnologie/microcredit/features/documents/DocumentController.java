package com.engtechnologie.microcredit.features.documents;

import com.engtechnologie.microcredit.features.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class DocumentController implements DocumentApi{

    private final DocumentService service;

    @Override
    public ResponseEntity<DocumentDto> create(Integer order, String name, String description, MultipartFile file) throws IOException {
        DocumentDto documentDto = DocumentDto.builder()
                .name(name)
                .description(description)
                .orderDto(OrderDto.builder().id(order).build())
                .build();
                documentDto = service.create(documentDto, file);
        return ResponseEntity.created(URI.create("/api/documents")).body(documentDto);

    }

    @Override
    public Collection<DocumentDto> getAll() {
        return service.getAll();
    }

    @Override
    public DocumentDto getById(Integer id) {
        return service.getById(id);
    }

    @Override
    public ResponseEntity<DocumentDto> update(Integer id, DocumentDto documentDto) {
        documentDto =  service.update(id, documentDto);
        return ResponseEntity.created(URI.create("/api/documents")).body(documentDto);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Collection<DocumentDto> getAllByOrder(Integer id) {
        return service.getAllByOrder(id);
    }
}
