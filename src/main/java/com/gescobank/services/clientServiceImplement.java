package com.gescobank.services;

import com.gescobank.cdto.ClientDto;
import com.gescobank.entities.Client;
import com.gescobank.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class clientServiceImplement implements clientService {
    private final ClientRepository clientRepository;
    clientServiceImplement(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public void createNewClient(ClientDto clientDto) {
        Client client = new Client();
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setEmail(clientDto.getEmail());
        client.setBirthday(clientDto.getBirthday());
        client.setTelephone(clientDto.getTelephone());

        this.clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client findOne(long id) {
        return this.clientRepository.getReferenceById(id);
    }
}
