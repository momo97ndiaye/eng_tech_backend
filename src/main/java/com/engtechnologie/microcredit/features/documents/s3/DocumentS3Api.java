package com.engtechnologie.microcredit.features.documents.s3;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/documents/s3")
@CrossOrigin(maxAge = 3600)
public interface DocumentS3Api {

    @DeleteMapping("/{fileName}")
    public ResponseEntity deleteDocument(@PathVariable String fileName);

    @GetMapping
    public List<String> getAllDocuments();

    @PostMapping
    public ResponseEntity uploadDocument(@RequestParam(value = "file") MultipartFile file) throws IOException;

    @GetMapping("/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException;

    @GetMapping("/presigned-url/{fileName}")
    public String presignedUrl(@PathVariable String fileName) throws IOException;
}
