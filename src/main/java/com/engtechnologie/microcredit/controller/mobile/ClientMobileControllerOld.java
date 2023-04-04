package com.engtechnologie.microcredit.controller.mobile;

import com.engtechnologie.microcredit.model.Client;
import com.engtechnologie.microcredit.services.ClientWebService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/clients_old")
public class ClientMobileControllerOld {

    ClientWebService clientService;

    public ClientMobileControllerOld(ClientWebService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("{id}")
    public Client getClientById(@PathVariable(value = "id") Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PutMapping
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping
    public void deleteClient(@PathVariable(value = "id") Long id) {
        clientService.deleteClient(id);
    }
}
