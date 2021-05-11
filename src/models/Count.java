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
public class Count {

    static Count count;

    public static Count Count() {
        if (count == null) {
            count = new Count();
        }
        return count;
    }
    int orange, airtel, vodacom, all;

    public int getSomme() {
        return this.orange + this.airtel + this.vodacom;
    }

    public Count() {
    }

    public int getOrange() {
        return orange;
    }

    public Count(int orange, int airtel, int vodacom) {
        this.orange = orange;
        this.airtel = airtel;
        this.vodacom = vodacom;
    }

    public void setOrange(int orange) {
        this.orange = orange;
    }

    public void setOrange() {
        this.orange++;
    }

    public int getAirtel() {
        return airtel;
    }

    public void setAirtel(int airtel) {
        this.airtel = airtel;
    }

    public void setAirtel() {
        this.airtel++;
    }

    public int getVodacom() {
        return vodacom;
    }

    public void setVodacom(int vodacom) {
        this.vodacom = vodacom;
    }

    public void setVodacom() {
        this.vodacom++;
    }

    public static Count getCount() {
        return count;
    }

    public static void setCount(Count count) {
        Count.count = count;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public void setAll() {
        this.all++;
    }

    int message, all_, traiter, en_attente;

    public Count(int message, int all_, int traiter, int en_attente) {
        this.message = message;
        this.all_ = all_;
        this.traiter = traiter;
        this.en_attente = en_attente;
    }

    public int getMessage() {
        return message;
    }

    public int getAll_() {
        return all_;
    }

    public int getTraiter() {
        return traiter;
    }

    public int getEn_attente() {
        return en_attente;
    }

    @Override
    public String toString() {
        return "Count{" + "all=" + all + ", message=" + message + ", all_=" + all_ + ", traiter=" + traiter + ", en_attente=" + en_attente + '}';
    }

}
