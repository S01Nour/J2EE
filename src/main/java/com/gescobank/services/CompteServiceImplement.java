package com.gescobank.services;

import com.gescobank.cdto.CompteDto;
import com.gescobank.entities.Client;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.CompteCourant;
import com.gescobank.entities.CompteEpargne;
import com.gescobank.enums.AccountStatus;
import com.gescobank.repositories.ClientRepository;
import com.gescobank.repositories.CompteBancaireRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompteServiceImplement implements CompteService {

    private final CompteBancaireRepository compteBancaireRepository;
    private final ClientRepository clientRepository;
    CompteServiceImplement(CompteBancaireRepository compteBancaireRepository,
                           ClientRepository clientRepository
    ){
        this.clientRepository = clientRepository;
        this.compteBancaireRepository = compteBancaireRepository;
    }

    @Override
    public void createAccount(CompteDto compteDto) {
        Optional<Client> clientOpt = this.clientRepository.findById(compteDto.getClientId());
        if (clientOpt.isPresent() && (compteDto.getDecouvert() > 0 && compteDto.getTauxInteret() == 0)) {
            CompteCourant compteCourant = new CompteCourant();
            compteCourant.setCreatedAt(new Date());
            compteCourant.setBalance(compteDto.getBalance());
            compteCourant.setClient(clientOpt.get());
            compteCourant.setDecouvert(compteDto.getDecouvert());
            compteCourant.setStatus(AccountStatus.ACTIVATED);
            compteCourant.setNumCompte(generateAccountNumber());

            this.compteBancaireRepository.save(compteCourant);
        }

        if (clientOpt.isPresent() && (compteDto.getDecouvert() == 0 && compteDto.getTauxInteret() > 0)) {
            CompteEpargne compteEpargne = new CompteEpargne();
            compteEpargne.setCreatedAt(new Date());
            compteEpargne.setBalance(compteDto.getBalance());
            compteEpargne.setClient(clientOpt.get());
            compteEpargne.setTauxInteret(compteDto.getTauxInteret());
            compteEpargne.setStatus(AccountStatus.ACTIVATED);
            compteEpargne.setNumCompte(generateAccountNumber());
            this.compteBancaireRepository.save(compteEpargne);
        }
    }

    @Override
    public List<CompteEpargne> findCompteEpargne() {
        List<CompteEpargne> list = new ArrayList<>();
        for(CompteBancaire c: compteBancaireRepository.findAll())
            if (c instanceof CompteEpargne)
                list.add((CompteEpargne) c);
        return list;
    }

    @Override
    public List<CompteCourant> findCompteCourant() {
        List<CompteCourant> list = new ArrayList<>();
        for(CompteBancaire c: compteBancaireRepository.findAll()) {
            if (c instanceof CompteCourant) {
                list.add((CompteCourant) c);
            }
        }
        return list;
    }

    @Override
    public CompteBancaire findOne(String numCompte) {
        return this.compteBancaireRepository.findByNumCompte(numCompte).get();
    }

    @Override
    public boolean activeCompte(String numCompte) {
        Optional<CompteBancaire> compte = this.compteBancaireRepository.findByNumCompte(numCompte);
        if (compte.isPresent() && compte.get().getStatus().equals(AccountStatus.SUSPENDED)) {
            CompteBancaire c = compte.get();
            c.setStatus(AccountStatus.ACTIVATED);
            this.compteBancaireRepository.save(c);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean suspendCompte(String numCompte) {
        Optional<CompteBancaire> compte = this.compteBancaireRepository.findByNumCompte(numCompte);
        if (compte.isPresent() && compte.get().getStatus().equals(AccountStatus.ACTIVATED)) {
            CompteBancaire c = compte.get();
            c.setStatus(AccountStatus.SUSPENDED);
            this.compteBancaireRepository.save(c);
            return true;
        }else {
            return false;
        }
    }

    private static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        //les 4 premiers chiffres sont 0
        for (int i = 0; i < 4; i++) {
            sb.append("0");
        }
        //les 4 derniers chiffres suivant sont 0 ou 1
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(2));
        }
        //les 10 deniers chiffres sont generes aleatoirement
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
