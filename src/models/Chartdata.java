/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author smartTicket
 */
public class Chartdata {

    String reseau;
    Count count;

    public Chartdata(String reseau, Count count) {
        this.reseau = reseau;
        this.count = count;
    }

    public String getReseau() {
        return reseau;
    }

    public Count getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Chartdata{" + "reseau=" + reseau + ", count=" + count + '}';
    }

}
