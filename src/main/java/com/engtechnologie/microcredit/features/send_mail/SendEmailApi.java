package com.engtechnologie.microcredit.features.send_mail;

import com.engtechnologie.microcredit.features.order.OrderStatusEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@RequestMapping("/api/send-email")
@CrossOrigin(maxAge = 3600)
interface SendEmailApi {

    @PostMapping("/send-attachment")
    ResponseEntity<Void> sendEmailWithAttachment(@RequestParam("to") String to,
                                                 @RequestParam("subject") String subject,
                                                 @RequestParam("text") String text,
                                                 @RequestParam("file") MultipartFile file);

    @PostMapping("/send-orders")
    ResponseEntity<Void> sendOrdersCsvFile(@RequestParam("to") String to,
                                           @RequestParam("subject") String subject,
                                           @RequestParam("text") String text) throws MessagingException, IOException;

    @PostMapping("/send-orders-by-statut")
    ResponseEntity<Void> sendOrdersByStatutCsvFile(@RequestParam("to") String to,
                                           @RequestParam("subject") String subject,
                                           @RequestParam("text") String text,
                                            @RequestParam("status") OrderStatusEnum statut) throws MessagingException, IOException;
}
