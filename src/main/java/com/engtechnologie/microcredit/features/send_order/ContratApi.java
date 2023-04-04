package com.engtechnologie.microcredit.features.send_order;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/send-contrat")
@CrossOrigin(maxAge = 3600)
public interface ContratApi {

    @PostMapping
    void sendContrat(@RequestParam("order") Integer orderId, @RequestParam("to") String to);
}
