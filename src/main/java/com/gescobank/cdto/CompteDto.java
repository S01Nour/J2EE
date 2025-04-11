package com.gescobank.cdto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompteDto {
    private double balance;
    private double tauxInteret;
    private double decouvert;
    private long clientId;

    public double getBalance() {
        return balance;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public long getClientId() {
        return clientId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
