package com.gescobank.entities;

import com.gescobank.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Operation implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private Date dateOperation;
    @Column(nullable = false)
    private String numOperation;
    @ManyToOne
    private CompteBancaire compte;

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public String getNumOperation() {
        return numOperation;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public void setNumOperation(String numOperation) {
        this.numOperation = numOperation;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Column(nullable = false)
    private TypeOperation typeOperation;
}
