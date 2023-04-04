package com.engtechnologie.microcredit.features.documents.s3;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentS3Service {
    @Value("${document.bucket-name}")
    private String bucketName;
    private final AmazonS3 amazonS3;

    public String uploadDocument(MultipartFile file) throws IOException {
        // file return extension
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String tempFileName = UUID.randomUUID() + file.getName() + extension;
        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/" + tempFileName);
        file.transferTo(tempFile);
        amazonS3.putObject(bucketName, tempFileName, tempFile);
        tempFile.deleteOnExit();

        return tempFileName;
    }

    public void deleteDocument(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);

    }
}
