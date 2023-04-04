package com.engtechnologie.microcredit.features.send_order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContratController implements ContratApi{

    private final ContratService service;

    @Override
    public void sendContrat(Integer orderId, String to) {

        service.sendContrat(orderId, to);

    }
}
