package com.gescobank.services;

import com.gescobank.cdto.CompteDto;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.CompteCourant;
import com.gescobank.entities.CompteEpargne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompteService {
    void createAccount(CompteDto compteDto);
    List<CompteEpargne> findCompteEpargne();
    List<CompteCourant> findCompteCourant();
    CompteBancaire findOne(String numCompte);


    boolean activeCompte(String numCompte);

    boolean suspendCompte(String numCompte);
}
