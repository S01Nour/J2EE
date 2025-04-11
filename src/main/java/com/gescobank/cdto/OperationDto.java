package com.gescobank.cdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    private long compteID;
    private double amount;
    private String numCompteSource;
    private String numCompteDestination;

    public long getCompteID() {
        return compteID;
    }

    public void setCompteID(long compteID) {
        this.compteID = compteID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNumCompteSource() {
        return numCompteSource;
    }

    public void setNumCompteSource(String numCompteSource) {
        this.numCompteSource = numCompteSource;
    }

    public String getNumCompteDestination() {
        return numCompteDestination;
    }

    public void setNumCompteDestination(String numCompteDestination) {
        this.numCompteDestination = numCompteDestination;
    }
}
