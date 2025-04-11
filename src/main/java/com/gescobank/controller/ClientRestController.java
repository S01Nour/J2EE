package com.gescobank.controller;

import com.gescobank.cdto.ClientDto;
import com.gescobank.entities.Client;
import com.gescobank.services.clientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//Controller
@RequestMapping(value = "/api/v1")
public class ClientRestController {
    private clientService clientService;
    ClientRestController(final clientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    void createClient(@RequestBody ClientDto dto){
        this.clientService.createNewClient(dto);
    }

    @GetMapping("/clients")
    List<Client> findAll (){
        return this.clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    Client findOne(@PathVariable("id") long id){
        return this.clientService.findOne(id);
    }

}

