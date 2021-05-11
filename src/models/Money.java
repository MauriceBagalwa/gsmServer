/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author smartTicket
 */
public class Money {

    String reference;
    float montant;
    String numero;
    float solde;
    String devise;
    String reseau;
    String sms;
    String date;
    boolean etat;
    boolean used;
    String code;

    public Money(String reference, float montant, String numero, float solde, String devise, String reseau, String sms) {
        this.reference = reference;
        this.montant = montant;
        this.numero = numero;
        this.solde = solde;
        this.devise = devise;
        this.reseau = reseau;
        this.sms = sms;
    }

    public Money(String reference, float montant, String numero, float solde, String devise, String reseau, String sms, String date, boolean etat, String code) {
        this.reference = reference;
        this.montant = montant;
        this.numero = numero;
        this.solde = solde;
        this.devise = devise;
        this.reseau = reseau;
        this.sms = sms;
        this.date = date;
        this.etat = etat;
        this.code = code;
    }
    public Money(String reference, float montant, String numero, float solde, String devise, String reseau, String sms, String date, boolean etat) {
        this.reference = reference;
        this.montant = montant;
        this.numero = numero;
        this.solde = solde;
        this.devise = devise;
        this.reseau = reseau;
        this.sms = sms;
        this.date = date;
        this.etat = etat;
    }

    public Money(String reseau, String numero, float montant, String devise, String date) {
        this.montant = montant;
        this.numero = numero;
        this.devise = devise;
        this.reseau = reseau;
        this.date = date;
    }

    public Money(String reference, String reseau, String numero, float montant, String devise, float solde, String sms) {
        this.reference = reference;
        this.montant = montant;
        this.numero = numero;
        this.solde = solde;
        this.devise = devise;
        this.reseau = reseau;
        this.sms = sms;
    }

    public Money(String reseau, float montant) {
        this.montant = montant;
        this.reseau = reseau;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getReseau() {
        return reseau;
    }

    public void setReseau(String reseau) {
        this.reseau = reseau;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Money{" + "reference=" + reference + ", montant=" + montant + ", numero=" + numero + ", solde=" + solde + ", devise=" + devise + ", reseau=" + reseau + ", sms=" + sms + ", date=" + date + ", etat=" + etat + '}';
    }

}
