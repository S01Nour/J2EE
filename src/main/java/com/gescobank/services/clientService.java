package com.gescobank.services;

import com.gescobank.cdto.ClientDto;
import com.gescobank.entities.Client;

import java.util.List;

public interface clientService {
    void createNewClient(ClientDto clientDto);
    List<Client> findAll();
    Client findOne(long id);
}
