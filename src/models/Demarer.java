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
public class Demarer {

    boolean demarer;
    String depuis;
    int message;

    public Demarer(boolean demarer, String depuis) {
        this.demarer = demarer;
        this.depuis = depuis;
    }

    public boolean isDemarer() {
        return demarer;
    }

    public void setDemarer(boolean demarer) {
        this.demarer = demarer;
    }

    public String getDepuis() {
        return depuis;
    }

    public void setDepuis(String depuis) {
        this.depuis = depuis;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

}
