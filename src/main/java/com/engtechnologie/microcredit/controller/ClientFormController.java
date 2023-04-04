package com.engtechnologie.microcredit.controller;

import com.engtechnologie.microcredit.model.ClientForm;
import com.engtechnologie.microcredit.repository.ClientFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-forms")
@RequiredArgsConstructor
public class ClientFormController {

    private final ClientFormRepository clientFormRepository;

    @GetMapping
    public List<ClientForm> getAllDemands() {
        return clientFormRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientForm> getDemandById(@PathVariable(value = "id") Long id)
            throws Exception {
        ClientForm demand = clientFormRepository.findById(id)
                .orElseThrow(() -> new Exception("Pas de demande avec l'id suivant :: " + id));
        return ResponseEntity.ok().body(demand);
    }

    @PostMapping
    public ClientForm createDemand(@RequestBody ClientForm clientForm) {
        return clientFormRepository.save(clientForm);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientForm> setOffer(@PathVariable long id, @RequestBody ClientForm clientForm) throws Exception{
        ClientForm offer = clientFormRepository.findById(id)
                .orElseThrow(() -> new Exception("Demand not found" + id));

        offer.setAllocatedSum(clientForm.getAllocatedSum());
        offer.setInterestRate(clientForm.getInterestRate());
        offer.setStatus(clientForm.getStatus());

        clientFormRepository.save(offer);

        return ResponseEntity.ok(offer);
    }
}
