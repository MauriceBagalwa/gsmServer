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
public class CserviceInfo {

    String port;
    int baud;
    String gsmDeviceManufacturer, gsmDeviceModel, centreMessagerie, pinsim;

    public CserviceInfo(String port, int baud, String gsmDeviceManufacturer, String gsmDeviceModel, String centreMass, String pin) {
        this.port = port;
        this.baud = baud;
        this.gsmDeviceManufacturer = gsmDeviceManufacturer;
        this.gsmDeviceModel = gsmDeviceModel;
        this.centreMessagerie = centreMass;
        this.pinsim = pin;
    }

    public String getPort() {
        return port;
    }

    public int getBaud() {
        return baud;
    }

    public String getGsmDeviceManufacturer() {
        return gsmDeviceManufacturer;
    }

    public String getGsmDeviceModel() {
        return gsmDeviceModel;
    }

    public String getCentreMessagerie() {
        return centreMessagerie;
    }

    public String getPinsim() {
        return pinsim;
    }

    @Override
    public String toString() {
        return "Cservice{" + "port=" + port + ", baud=" + baud + ", gsmDeviceManufacturer=" + gsmDeviceManufacturer + ", gsmDeviceModel=" + gsmDeviceModel + ", centreMessagerie=" + centreMessagerie + ", pinsim=" + pinsim + '}';
    }

}
