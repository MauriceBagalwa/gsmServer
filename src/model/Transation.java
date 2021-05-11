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
public class Transation {

    String number;
    String reference;
    String montant;
    String devise;
    String telecom;
    String solde;
    String sms;

    public Transation() {
    }

    public Transation(String number, String reference, String montant, String devise, String telecom, String solde, String sms) {
        this.number = number;
        this.reference = reference;
        this.montant = montant;
        this.devise = devise;
        this.telecom = telecom;
        this.solde = solde;
        this.sms = sms;
    }

    public String getNumber() {
        return number;
    }

    public String getReference() {
        return reference;
    }

    public String getMontant() {
        return montant;
    }

    public String getDevise() {
        return devise;
    }

    public String getTelecom() {
        return telecom;
    }

    public String getSolde() {
        return solde;
    }

    public String getSms() {
        return sms;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public void setTelecom(String telecom) {
        this.telecom = telecom;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "Transation{" + "number=" + number + ", reference=" + reference + ", montant=" + montant + ", devise=" + devise + ", telecom=" + telecom + ", solde=" + solde + ", sms=" + sms + '}';
    }
    
}
