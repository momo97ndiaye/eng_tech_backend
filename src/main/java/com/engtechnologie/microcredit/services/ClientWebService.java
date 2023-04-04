package com.engtechnologie.microcredit.services;

import com.engtechnologie.microcredit.model.Client;
import com.engtechnologie.microcredit.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientWebService {

    ClientRepository clientRepository;

    public ClientWebService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //create client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
    //update client
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }
    //get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    //get client by id
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    //delete client
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
