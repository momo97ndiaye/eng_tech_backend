package com.engtechnologie.microcredit.features.send_mail;

import com.engtechnologie.microcredit.features.order.OrderEntity;
import com.engtechnologie.microcredit.features.order.OrderRepository;
import com.engtechnologie.microcredit.features.order.OrderStatusEnum;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendEmailService {
    private final JavaMailSender javaMailSender;
    private final OrderRepository orderRepository;

    public String sendEmailWithAttachment(String to, String subject, String text, MultipartFile file) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();

            // set recipient, subject, and message text
            msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(text);

            // attach the file
            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(convertMultipartToFile(file));
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachPart);
            msg.setContent(multipart);

            javaMailSender.send(msg);
            return "email sent successfully";
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return "error while sending email";
        }
    }

    public void sendOrdersCSV(String to, String subject, String text) throws IOException, MessagingException {
        List<OrderEntity> orders = orderRepository.findAll();
        sendEmail(to, subject, text, orders);
    }

    public void sendOrdersByStatutCSV(String to, String subject, String text, OrderStatusEnum statut) throws MessagingException, IOException {
        Collection<OrderEntity> orders = orderRepository.findAllByStatus(statut);
        sendEmail(to, subject, text, orders.stream().toList());
    }


    public void sendEmail(String to, String subject, String text, File file) throws IOException, MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setText(text);
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile(file);
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        javaMailSender.send(msg);
    }

    private void sendEmail(String to, String subject, String text, List<OrderEntity> orders) throws IOException, MessagingException {
        File file = createCSVFile(orders);
        MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setText(text);
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile(file);
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        javaMailSender.send(msg);
    }

    private File convertMultipartToFile(MultipartFile file) throws IOException {
        if(file == null) {
            throw new IllegalArgumentException("File is null");
        }
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private File createCSVFile(List<OrderEntity> orders) throws IOException {
        String[] header = {"id", "code", "montant", "date", "client"};
        File file = new File("orders.csv");
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);
        writer.writeNext(header);
        for (OrderEntity order : orders) {
            String[] data = {String.valueOf(order.getId()), order.getCode(), order.getAmount().toString(), order.getOrderDate().toString(), order.getCustomer().getId().toString()};
            writer.writeNext(data);
        }
        writer.close();
        return file;
    }


}
