package com.gescobank.services;

import com.gescobank.cdto.OperationDto;
import com.gescobank.entities.CompteBancaire;
import com.gescobank.entities.Operation;

import java.util.List;

public interface OperationService {

    CompteBancaire effectuerVersement(OperationDto dto);

    CompteBancaire effectuerRetrait(OperationDto dto);

    boolean effectuerVirement(OperationDto dto);

    List<Operation> findByClientNumCompte(String numCompte);
}
