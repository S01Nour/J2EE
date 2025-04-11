package com.gescobank.controller;

import com.gescobank.services.clientService;
import com.gescobank.entities.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClientViewController {

    private final clientService clientService;

    public ClientViewController(clientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients/view")
    public String showClients(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "clients"; // correspond à clients.html
    }
}
