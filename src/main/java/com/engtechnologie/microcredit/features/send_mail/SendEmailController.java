package com.engtechnologie.microcredit.features.send_mail;

import com.engtechnologie.microcredit.features.order.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SendEmailController implements SendEmailApi {

    private final SendEmailService service;

    @Override
    public ResponseEntity<Void> sendEmailWithAttachment(String to, String subject, String text, MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<Void> sendOrdersCsvFile(String to, String subject, String text) throws MessagingException, IOException {
        service.sendOrdersCSV(to, subject, text);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> sendOrdersByStatutCsvFile(String to, String subject, String text, OrderStatusEnum statut) throws MessagingException, IOException {
        service.sendOrdersByStatutCSV(to, subject, text, statut);

        return ResponseEntity.noContent().build();
    }


}
