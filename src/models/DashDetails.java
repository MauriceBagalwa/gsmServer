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
public class DashDetails {

    String reseau;
    int message, money, traiter, en_attente, total;

    public DashDetails(String reseau, int message, int money, int traiter, int en_attente) {
        this.reseau = reseau;
        this.message = message;
        this.money = money;
        this.traiter = traiter;
        this.en_attente = en_attente;
        this.total = (message + en_attente + traiter);
    }

    public int getMessage() {
        return message;
    }

    public void setMessage() {
        this.message++;
    }

    public int getTraiter() {
        return traiter;
    }

    public void setTraiter() {
        this.traiter++;
    }

    public int getEn_attente() {
        return en_attente;
    }

    public void setEn_attente() {
        this.en_attente++;
    }

    public String getReseau() {
        return reseau;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        this.total++;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney() {
        this.money++;
    }

    @Override
    public String toString() {
        return "DashDetails{" + "reseau=" + reseau + ", message=" + message + ", money=" + money + ", traiter=" + traiter + ", en_attente=" + en_attente + ", total=" + total + '}';
    }


}
