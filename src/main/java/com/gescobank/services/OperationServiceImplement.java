package com.gescobank.services;

import com.gescobank.cdto.OperationDto;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.Operation;
import com.gescobank.enums.AccountStatus;
import com.gescobank.enums.TypeOperation;
import com.gescobank.repositories.CompteBancaireRepository;
import com.gescobank.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OperationServiceImplement implements OperationService {

    private final CompteBancaireRepository compteBancaireRepository;
    private final OperationRepository operationRepository;

    public OperationServiceImplement(CompteBancaireRepository compteBancaireRepository,
                                     OperationRepository operationRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public CompteBancaire effectuerVersement(OperationDto dto) {
        Optional<CompteBancaire> compteOpt = this.compteBancaireRepository.findByNumCompte(dto.getNumCompteSource());
        if (compteOpt.isPresent()) {
            CompteBancaire compte = compteOpt.get();
            if (compte.getStatus().equals(AccountStatus.ACTIVATED)) {
                compte.setBalance(compte.getBalance() + dto.getAmount());
                Operation operation = new Operation();
                operation.setCompte(compte);
                operation.setDateOperation(new Date());
                operation.setAmount(dto.getAmount());
                operation.setTypeOperation(TypeOperation.CREDIT);
                operation.setNumOperation(generateAccountNumber());
                this.operationRepository.save(operation);
                return this.compteBancaireRepository.save(compte);

            } else {
                throw new RuntimeException("Le compte est suspendu.");
            }
        } else {
            throw new RuntimeException("Ce compte n'existe pas.");
        }
    }

    @Override
    public CompteBancaire effectuerRetrait(OperationDto dto) {
        Optional<CompteBancaire> compteOpt = compteBancaireRepository.findByNumCompte(dto.getNumCompteSource());
        if (compteOpt.isPresent()) {
            CompteBancaire compte = compteOpt.get();
            if (compte.getStatus().equals(AccountStatus.ACTIVATED) && compte.getBalance() >= dto.getAmount()) {
                compte.setBalance(compte.getBalance() - dto.getAmount());
                compte = this.compteBancaireRepository.save(compte);
                Operation operation = new Operation();
                operation.setDateOperation(new Date());
                operation.setAmount(dto.getAmount());
                operation.setTypeOperation(TypeOperation.DEBIT);
                operation.setCompte(compte);
                operation.setNumOperation(generateAccountNumber());
                this.operationRepository.save(operation);
                return compte;
            } else {
                throw new RuntimeException("Fonds insuffisants ou compte suspendu.");
            }
        } else {
            throw new RuntimeException("Ce compte n'existe pas.");
        }
    }


    @Override
    public boolean effectuerVirement(OperationDto dto) {
        String numCompteSource = dto.getNumCompteSource();
        OperationDto dtoSource = new OperationDto();
        //OperationDto dtoSource = new OperationDto(numCompteSource, null,dto.getAmount());
        CompteBancaire compteBancaireSource = effectuerRetrait(dtoSource);
        if (compteBancaireSource!=null){
             String numCompteDestination = dto.getNumCompteDestination();
             OperationDto dtoDestination = new OperationDto();
             //OperationDto dtoDestination = new OperationDto(null, numCompteDestination, dto.getAmount());
             effectuerVersement(dtoDestination);
             return true;
        }
        return false;
    }

    @Override
    public List<Operation> findByClientNumCompte(String numCompte) {
        List<Operation> list = new ArrayList<>();
        for (Operation o:this.operationRepository.findAll()){
            if (o.getCompte().getNumCompte().equals(numCompte)){
                list.add(o);
            }
        }
        return list;
    }
    //public boolean effectuerVirement(OperationDto dto) {
      //  String numSource = dto.getNumCompteSource();
       // String numDest = dto.getNumCompteDetination();
        //double montant = dto.getAmount();

        //if (numSource.equals(numDest)) {
            //throw new RuntimeException("Les comptes source et destination doivent être différents.");
        //}

        //OperationDto retraitDto = new OperationDto(numSource, null, montant);
        //CompteBancaire source = effectuerRetrait(retraitDto);

        //if (source != null) {
            //OperationDto versementDto = new OperationDto(null, numDest, montant);
            //effectuerVersement(versementDto);
            //return true;
        //}
        //return false;
    //}

    private static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // 4 chiffres "0"
        sb.append("0");

        // 4 chiffres entre 0 et 1
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(2));
        }

        // 10 chiffres aléatoires
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
