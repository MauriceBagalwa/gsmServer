/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class Money {

    String code;
    String reference;
    float montant;
    String devise;
    String date;
    String solde;
    String refArgent;
    String numero;
    String etat;

    public Money() {
    }

    public Money(String code, String reference, float montant, String devise, String date, String solde, String refArgent, String numero, String etat) {
        this.code = code;
        this.reference = reference;
        this.montant = montant;
        this.devise = devise;
        this.date = date;
        this.solde = solde;
        this.refArgent = refArgent;
        this.numero = numero;
        this.etat = etat;
    }

    public String getCode() {
        return code;
    }

    public String getReference() {
        return reference;
    }

    public float getMontant() {
        return montant;
    }

    public String getDevise() {
        return devise;
    }

    public String getDate() {
        return date;
    }

    public String getSolde() {
        return solde;
    }

    public String getRefArgent() {
        return refArgent;
    }

    public String getNumero() {
        return numero;
    }

    public String getEtat() {
        return etat;
    }

    @Override
    public String toString() {
        return "Money{" + "code=" + code + ", reference=" + reference + ", montant=" + montant + ", devise=" + devise + ", date=" + date + ", solde=" + solde + ", refArgent=" + refArgent + ", numero=" + numero + ", etat=" + etat + '}';
    }
    
    
}
