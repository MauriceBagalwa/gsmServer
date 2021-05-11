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
public class Detailsmoney {

    float airtel, orange, vodacom;

    public Detailsmoney(float airtel, float orange, float vodacom) {
        this.airtel = airtel;
        this.orange = orange;
        this.vodacom = vodacom;
    }

    public float getAirtel() {
        return airtel;
    }

    public float getOrange() {
        return orange;
    }

    public float getVodacom() {
        return vodacom;
    }

}
