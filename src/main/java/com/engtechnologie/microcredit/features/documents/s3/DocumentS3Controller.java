package com.engtechnologie.microcredit.features.documents.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DocumentS3Controller implements DocumentS3Api {

    @Value("${document.bucket-name}")
    private String bucketName;

    final private AmazonS3 amazonS3;


    @Override
    public ResponseEntity deleteDocument(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<String> getAllDocuments() {
        return amazonS3.listObjectsV2(bucketName).getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity uploadDocument(MultipartFile file) throws IOException {
        String tempFileName = UUID.randomUUID() + file.getName();
        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + tempFileName);
        file.transferTo(tempFile);
        amazonS3.putObject(bucketName, UUID.randomUUID() + file.getName(), tempFile);
        tempFile.deleteOnExit();
        return ResponseEntity.created(URI.create("SOME-LOCATION")).build();
    }

    @Override
    public ResponseEntity<ByteArrayResource> downloadFile(String fileName) throws IOException {
        S3Object data = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream objectContent = data.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectContent);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        objectContent.close();
        return ResponseEntity
                .ok()
                .contentLength(bytes.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @Override
    public String presignedUrl(String fileName) throws IOException {
        return amazonS3.generatePresignedUrl(bucketName, fileName, convertToDateViaInstant(LocalDate.now().plusDays(1))).toString();
    }

    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
