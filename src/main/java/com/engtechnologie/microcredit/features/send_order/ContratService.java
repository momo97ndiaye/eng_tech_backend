package com.engtechnologie.microcredit.features.send_order;

import com.engtechnologie.microcredit.features.order.*;
import com.engtechnologie.microcredit.features.send_mail.SendEmailService;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratService {

    private final TemplateEngine templateEngine;

    private final OrderRepository orderRepository;

    private final SendEmailService sendEmailService;

    @Value("${pdf.directory}")
    private String pdfDirectory;


    public void sendContrat(Integer orderId, String to) {

       Optional<OrderEntity> optionalOrderEntity =  orderRepository.findById(orderId);

        if(optionalOrderEntity.isPresent()){
            OrderEntity order = optionalOrderEntity.get();
            Map<String, Object> data = Map.of(
                    "order", order
            );

            generatePdfFile("contrat", data , order.getCode()+".pdf");

            File file = new File(pdfDirectory + order.getCode()+".pdf");

            try {
                order.setStatus(OrderStatusEnum.SENT);
                orderRepository.save(order);
                sendEmailService.sendEmail(to, "Demande de credit", "Contrat", file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        } else{
            throw new RuntimeException("Order not found");
        }

    }

    private void generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName) {
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process(templateName, context);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pdfDirectory + pdfFileName);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(fileOutputStream, false);
            renderer.finishPDF();
        } catch (FileNotFoundException e) {
            //logger.error(e.getMessage(), e);
        } catch (DocumentException e) {
            // logger.error(e.getMessage(), e);
        }
    }


}
