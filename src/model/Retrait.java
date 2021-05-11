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
public class Retrait {

//    int id;
//    String motif;
//    double montant;
//    double frais;
//    String pays;
//    String numero;
//    String operateur;
//    String dateEn;
//    String dateRetrait;
//    int refCompte;
//    boolean finished;
    String id;
    String motif;
    float montant;
    String numero;
    String operateur;
    String dateEn;
    int refCompte;
    int finished;
    String nomAgent;

    String datevalider;
    String agentValidate;

//    {"id"
//    
//
//    :"17",
// "motif":"RETRAIT D'ARGENT AGENCE",
// "montant":"100",
// "numero":"975236270",
// "operateur":"AIRTEL",
// "dateEn":"2021-03-05 08:30:00",
// "refCompte":"98",
// "finished":"1",
// "nomAgent":"Jack Renard"}   
    public Retrait(String id, String motif, float montant, String numero, String operateur, String dateEn, int refCompte, int finished, String nomAgent) {
        this.id = id;
        this.motif = motif;
        this.montant = montant;
        this.numero = numero;
        this.operateur = operateur;
        this.dateEn = dateEn;
        this.refCompte = refCompte;
        this.finished = finished;
        this.nomAgent = nomAgent;
    }

    public Retrait(String id, String agentValidate) {
        this.id = id;
        this.agentValidate = agentValidate;
    }

    public Retrait(String id, String motif, float montant, String numero, String operateur, String dateEn, int refCompte, int finished, String nomAgent, String datevalider) {
        this.id = id;
        this.motif = motif;
        this.montant = montant;
        this.numero = numero;
        this.operateur = operateur;
        this.dateEn = dateEn;
        this.refCompte = refCompte;
        this.finished = finished;
        this.nomAgent = nomAgent;
        this.datevalider = datevalider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
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

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public String getDateEn() {
        return dateEn;
    }

    public void setDateEn(String dateEn) {
        this.dateEn = dateEn;
    }

    public int getRefCompte() {
        return refCompte;
    }

    public void setRefCompte(int refCompte) {
        this.refCompte = refCompte;
    }

    public int isFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public String getDatevalider() {
        return datevalider;
    }

    @Override
    public String toString() {
        return "Retrait{" + "id=" + id + ", motif=" + motif + ", montant=" + montant + ", numero=" + numero + ", operateur=" + operateur + ", dateEn=" + dateEn + ", refCompte=" + refCompte + ", finished=" + finished + ", nomAgent=" + nomAgent + '}';
    }

}
