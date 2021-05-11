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
public class Session {

    boolean conncet;
    String Manufacturer, Model, SerialNo, SIMIMSI, SignalLevel;

    public Session(boolean conncet, String Manufacturer, String Model, String SerialNo, String SIMIMSI, String SignalLevel) {
        this.conncet = conncet;
        this.Manufacturer = Manufacturer;
        this.Model = Model;
        this.SerialNo = SerialNo;
        this.SIMIMSI = SIMIMSI;
        this.SignalLevel = SignalLevel;
    }

    public boolean isConncet() {
        return conncet;
    }

    public void setConncet(boolean conncet) {
        this.conncet = conncet;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String SerialNo) {
        this.SerialNo = SerialNo;
    }

    public String getSIMIMSI() {
        return SIMIMSI;
    }

    public void setSIMIMSI(String SIMIMSI) {
        this.SIMIMSI = SIMIMSI;
    }

    public String getSignallevel() {
        return SignalLevel;
    }

    public void setSignallevel(String Signallevel) {
        this.SignalLevel = Signallevel;
    }

}
