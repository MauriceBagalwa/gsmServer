/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import models.Session;
import static helpers.Traitement.airtelBool;
import static controllers.ServeurController.messageError;
import static controllers.ServeurController.messageErrorOrange;
import static controllers.ServeurController.messageErrorVoda;
import models.CserviceInfo;
import models.DetailServer;
import org.smslib.CService;

/**
 *
 * @author smartTicket
 */
public class Activation {

    /**
     * @Variables Cservice
     */
    public static CService airtelService;
    public static CService voadacomService;
    public static CService OrangeService;
    public static Session airtelSession;
    public static Session orangeSession;
    public static Session vodacomSession;

    /**
     * @param cservice
     * @return
     * @Activation du CService d'airtel
     */
    public static boolean activedAirtel(CserviceInfo cservice) {
        try {
            System.out.println("#1 Tentatives de connexion...");
            /*
        @initialisation du CService
             */
            airtelService = new CService(cservice.getPort(), cservice.getBaud(),
                    cservice.getGsmDeviceManufacturer(), cservice.getGsmDeviceModel());
            /*
        @Autre configuration
             */
            airtelService.setSimPin(cservice.getPinsim());
            airtelService.setSimPin2(cservice.getPinsim());
            airtelService.setSmscNumber(cservice.getCentreMessagerie());
            /*
        @connexion
             */
            airtelService.connect();
            airtelSession = new Session(true, airtelService.getDeviceInfo().getManufacturer(),
                    airtelService.getDeviceInfo().getModel(), airtelService.getDeviceInfo().getSerialNo(),
                    airtelService.getDeviceInfo().getImsi(), airtelService.getDeviceInfo().getSignalLevel() + "%");
            System.out.println("Etat: " + airtelService.getDeviceInfo().getSignalLevel() + "%");
            return true;
        } catch (Exception e) {
            messageError = e.getMessage();
            airtelBool = false;
        }
        return false;
    }

    /**
     * @param cservice
     * @return
     * @Activation du CService d'airtel
     */
    public static boolean activedVodacom(CserviceInfo cservice) {
        try {
            System.out.println("#1 Tentatives de connexion...");
            /*
        @initialisation du CService
             */
            voadacomService = new CService(cservice.getPort(), cservice.getBaud(),
                    cservice.getGsmDeviceManufacturer(), cservice.getGsmDeviceModel());
            /*
        @Autre configuration
             */
            voadacomService.setSimPin(cservice.getPinsim());
            voadacomService.setSimPin2(cservice.getPinsim());
            voadacomService.setSmscNumber(cservice.getCentreMessagerie());
            /*
        @connexion
             */
            voadacomService.connect();
            vodacomSession = new Session(true, voadacomService.getDeviceInfo().getManufacturer(),
                    voadacomService.getDeviceInfo().getModel(), voadacomService.getDeviceInfo().getSerialNo(),
                    voadacomService.getDeviceInfo().getImsi(), voadacomService.getDeviceInfo().getSignalLevel() + "%");
            System.out.println("Etat: " + voadacomService.getDeviceInfo().getSignalLevel() + "%");
            return true;
        } catch (Exception e) {
            messageErrorVoda = "voda " + e.getMessage();
            if (e.getMessage() == null) {
                messageErrorVoda = "absence du PortCOM";
            }
        }
        return false;
    }

    /**
     * @param cservice
     * @return
     * @Activation du CService d'airtel
     */
    public static boolean activedOrange(CserviceInfo cservice) {
        try {
            System.out.println("voila #3");
            /*
        @initialisation du CService
             */
            OrangeService = new CService(cservice.getPort(), cservice.getBaud(),
                    cservice.getGsmDeviceManufacturer(), cservice.getGsmDeviceModel());
            /*
        @Autre configuration
             */
            OrangeService.setSimPin(cservice.getPinsim());
            OrangeService.setSimPin2(cservice.getPinsim());
            OrangeService.setSmscNumber(cservice.getCentreMessagerie());
            /*
        @connexion
             */
            OrangeService.connect();
            orangeSession = new Session(true, OrangeService.getDeviceInfo().getManufacturer(),
                    OrangeService.getDeviceInfo().getModel(), OrangeService.getDeviceInfo().getSerialNo(),
                    OrangeService.getDeviceInfo().getImsi(), OrangeService.getDeviceInfo().getSignalLevel() + "%");
            System.out.println("Etat: " + OrangeService.getDeviceInfo().getSignalLevel() + "%");
//            Detail = new DetailServer("Orange", OrangeService.getDeviceInfo().getSignalLevel());
//            ListDetailserver.add(Detail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            messageErrorOrange = e.getMessage();
        }
        return false;
    }

}
