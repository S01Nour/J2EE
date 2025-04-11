package com.gescobank.controller;


import com.gescobank.cdto.CompteDto;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.CompteCourant;
import com.gescobank.entities.CompteEpargne;
import com.gescobank.services.CompteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class CompteBancaireRestController {
    private final CompteService compteService;

    CompteBancaireRestController(final CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping("/comptes")
    void createAccount(@RequestBody CompteDto compteDto) {
        this.compteService.createAccount(compteDto);
    }

    @GetMapping("/comptes/type/{type}")
    List<?> findAll(@PathVariable("type") String type) {
        if (type.equals("cc"))
            return this.compteService.findCompteCourant();

        if (type.equals("ce"))
            return this.compteService.findCompteEpargne();

        return null;
    }

    @GetMapping("/comptes/{numCompte}/{type}")
    ResponseEntity<?> findCompte(@PathVariable("numCompte") String numCompte,
                                 @PathVariable("type") String type) {
        CompteBancaire compteBancaire = this.compteService.findOne(numCompte);
        if (type.equals("cc") && (compteBancaire instanceof CompteCourant))
            return ResponseEntity.ok((CompteCourant) compteBancaire);
        if (type.equals("ce") && (compteBancaire instanceof CompteEpargne))
            return ResponseEntity.ok((CompteEpargne) compteBancaire);
        return null;
    }

    @GetMapping("/comptes/active/{numCompte}")
    boolean activeCompte(@PathVariable("numCompte") String numCompte) {
        return this.compteService.activeCompte(numCompte);
    }

    @GetMapping("/comptes/suspendre/{numCompte}")
    boolean suspendreCompte(@PathVariable("numCompte") String numCompte) {
        return this.compteService.suspendCompte(numCompte);
    }


}

