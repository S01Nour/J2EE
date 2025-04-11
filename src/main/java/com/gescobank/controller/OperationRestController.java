package com.gescobank.controller;

import com.gescobank.cdto.OperationDto;
import com.gescobank.entities.Operation;
import com.gescobank.services.OperationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class OperationRestController {
    final OperationService operationService;
    OperationRestController(final OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/operations/versement")
    boolean effectuerVersement(@RequestBody OperationDto dto) {
        this.operationService.effectuerVersement(dto);
        return true;
    }

    @PostMapping("/operations/retrait")
    boolean effectuerRetrait(@RequestBody OperationDto dto) {
        this.operationService.effectuerRetrait(dto);
        return true;
    }

    @PostMapping("/operations/virement")
    boolean virement(@RequestBody OperationDto dto) {
        return this.operationService.effectuerVirement(dto);
    }
    @PostMapping("/operations/client/{numCompte}")
    List<Operation> findAllOperationByClient(@PathVariable ("numCompte") String numCompte) {
        return this.operationService.findByClientNumCompte(numCompte);
    }
}
