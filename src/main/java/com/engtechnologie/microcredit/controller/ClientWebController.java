package com.engtechnologie.microcredit.controller;


import com.engtechnologie.microcredit.model.Client;
import com.engtechnologie.microcredit.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
public class ClientWebController {


    private final ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity< Client > getClientById(@PathVariable(value = "id") Long id)
            throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Pas de client avec l'id suivant :: " + id));
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
