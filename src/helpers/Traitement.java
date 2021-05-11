/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import static apiActivity.RetraitActivity.retraitInstence;
import bd.Operations;
import static bd.Operations.MessageOrangeMoney;
import static bd.Operations.Operation;
import static controllers.DonneeController.getData;
import models.Demarer;
import static controllers.MainController.ListDetailserver;
import static controllers.MainController.getMessages;
import static controllers.MainController.isParametre;
import static controllers.TraitementController.*;
import static helpers.Events.BouncaAnnimet;
import static helpers.Events.Events_Instance;
import static helpers.FormatMessage.isAirtel;
import static helpers.FormatMessage.isM_PESA;
import static helpers.FormatMessage.model_OrangeMoney;
import static helpers.Loading.Loading;
import static helpers.rooters.MESSAGE_RECEV;
import static helpers.rooters.SQL_MESSAGES_OFDAY;
import static controllers.DashbordController.Lastmessage;
import static controllers.DashbordController.lastmmssVBX_;
import static helpers.Activation.airtelService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import model.Retrait;
import static models.Count.Count;
import models.DetailServer;
import models.Message;
import models.Money;
import org.smslib.CIncomingMessage;
import org.smslib.CMessage;
import org.smslib.COutgoingMessage;
import org.smslib.CService;

/**
 *
 * @author smartTicket
 */
public class Traitement {

    /**
     * @Variables
     */
    public static Task<Void> Airtel;
    public static Task<Void> Orange;
    public static Task<Void> Vodacom;
    static Date date_ = new Date();
    public static String messageAirtel;
    public static String messageOrange;
    public static String messageVodacom;
    static Message messageTosave, formatMssg;
    static Money airtelmoney, mpesa, orangemoney;
    public static Demarer Demarer_airtel;
    public static Demarer Demarer_orange;
    public static Demarer Demarer_vodacom;
    static DetailServer Detail;

    /**
     * @Fonction & Methodes
     */
    /*@1*/
    public static boolean airtelBool;

    public static void AirtelInaction(CService cService, LinkedList messageList) {
        Airtel = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Demarer_airtel = new Demarer(true, date_.toLocaleString());
                Detail = new DetailServer("Airtel", cService.getDeviceInfo().getSignalLevel());
                ListDetailserver.add(Detail);
                while (airtelBool) {
                    ReceptionMessage(cService, "airtel", messageList);
                }
                return null;
            }
        };
        new Thread(Airtel).start();
    }

    /*@2*/
    public static void VodacomInaction(CService cService, LinkedList messageList) {
        Vodacom = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Demarer_vodacom = new Demarer(true, date_.toLocaleString());
                System.out.println("%" + cService.getDeviceInfo().getSignalLevel());
                Detail = new DetailServer("Vodacom", cService.getDeviceInfo().getSignalLevel());
                ListDetailserver.add(Detail);
                while (true) {
                    ReceptionMessage(cService, "vodacom", messageList);

                }
            }
        };
        new Thread(Airtel).start();
    }

    /*@3*/
    public static void OrangeIn_action(CService cService, LinkedList messageList) {
        Orange = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Demarer_orange = new Demarer(true, date_.toLocaleString());
                System.out.println("%" + cService.getDeviceInfo().getSignalLevel());
                Detail = new DetailServer("Orange", cService.getDeviceInfo().getSignalLevel());
                ListDetailserver.add(Detail);
                while (true) {
                    ReceptionMessage(cService, "orange", messageList);
                }
            }
        };
        new Thread(Orange).start();
    }
    /**
     * @param cService
     * @param reseau
     * @param messageList
     * @Fonction & Methodes
     */
    static CIncomingMessage msg;

    static void ReceptionMessage(CService cService, String reseau, LinkedList messageList) {
        try {
            messageList.clear();
            cService.readMessages(messageList, CIncomingMessage.MessageClass.All, 1);
            messageList.stream().map(message -> {
                return message;
            }).forEachOrdered(message -> {
                try {
                    msg = (CIncomingMessage) message;
                    messageTosave = new Message(dte.toString(), msg.getOriginator(), reseau, msg.getText().trim());
                    Count().setAll();
                    if (getMessages.size() == 2) {
                        getMessages.remove(0);
                    }

                    getMessages.add(messageTosave);
                    System.out.println("size: " + getMessages.size());
                    System.out.println("Mssg: " + msg.getText());

                    if (Operation().saveSMS(messageTosave)) {

                        if (isParametre.equals("traitement")) {
                            messages = Operation().messageOfday(SQL_MESSAGES_OFDAY);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        countMessage_.setText(Events_Instance().formatNumber(messages.size()));
                                        Loading().ScrollOperation(vbxMssg_, messages, offset, limit, MESSAGE_RECEV);
                                        sizeOfdata = messages.size();
                                        Aindex_.setText((offset + 1) + " à " + limit + " de " + sizeOfdata);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                        } else if (isParametre.equals("dashBord")) {
                            Lastmessage = Operation().LastmessageOfday(SQL_MESSAGES_OFDAY + " limit 3");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Loading().LoadVBX(lastmmssVBX_, Lastmessage);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                        }
                        switch (reseau) {
                            case "airtel": {
                                System.out.println("-> Voila 2 Airtel");
                                try {
                                    airtelmoney = isAirtel(msg.getText().trim());
                                    System.out.println("Money: " + airtelmoney.toString());
                                    if (airtelmoney != null && msg.getOriginator().equals("+436") || msg.getOriginator().equals("+243973264761")) {
//                                    if (airtelmoney != null && msg.getOriginator().equals("+436") || msg.getOriginator().equals("436")) {
                                        sizeTraitement = Operation().getMessage(Operations.MessageAirtelMoney, airtelmoney).size();
                                        System.out.println("-> Voila 3 Airtel");
                                        try {
                                            if (Operation().saveMoney(airtelmoney)) {
                                                System.out.println("-> Voila 4 Airtel");
                                                Count().setAirtel();
                                                if (isParametre.equals("traitement")) {
                                                    increment(reseau);
                                                }
                                            }
                                            cService.deleteMessage(msg);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            cService.deleteMessage(msg);
                                        }
                                    } else {
                                        cService.deleteMessage(msg);
                                    }
                                } catch (Exception e) {
                                    cService.deleteMessage(msg);
                                }
                                break;
                            }
                            case "orange": {
                                System.out.println("-> Voila 2 Orange");
                                orangemoney = model_OrangeMoney(msg.getText());
                                System.out.println(orangemoney.toString());
//                                if (msg.getOriginator().equals("OrangeMoney") || msg.getOriginator().equals("+243858974052") && orangemoney != null) {
                                if (msg.getOriginator().equals("OrangeMoney") && orangemoney != null) {
                                    System.out.println("Yes...");
                                    sizeTraitementOrange = Operation().getMessage(MessageOrangeMoney, orangemoney).size();
                                    System.out.println("-> Voila 3");
                                    try {
                                        if (Operation().saveMoney(orangemoney)) {
                                            System.out.println("VOILA 4::- Insertion du money dans la DB -::");
                                            Count().setOrange();
                                            if (isParametre.equals("traitement")) {
                                                increment(reseau);
                                            }
                                            cService.deleteMessage(msg);
                                        }
                                    } catch (Exception e) {

                                        System.out.println("Duplicate... " + e.getMessage());
                                        System.out.println(orangemoney.toString());

                                        cService.deleteMessage(msg);
                                    }
                                } else {
                                    System.out.println("Num: " + msg.getOriginator());
                                    cService.deleteMessage(msg);
                                }
                                break;
                            }
                            case "vodacom": {
                                if (msg.getOriginator().equals("M-PESA") && isM_PESA(msg.getText().trim())) {
                                    cService.deleteMessage(msg);
                                }
                                break;
                            }
                        }
                        if (isParametre.equals("traitement")) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    hors_.setText(Events_Instance().formatNumber(messages.size() - Count().getSomme()));
                                }
                            });

                        } else if (isParametre.equals("donnees")) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        getData();
                                    } catch (Exception ex) {
                                        Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });

                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        cService.deleteMessage(msg);
                        System.out.println("Message Non pris en charge");
                    } catch (Exception ex1) {
                        Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(":::--- Server out...");
        }
    }

    /**
     *
     * @Envoie message
     * @param recipient
     * @param sms
     * @param cService
     * @return
     */
    public static boolean SendMoney(String recipient, String sms, CService cService) {
        try {
            COutgoingMessage mySms = new COutgoingMessage(recipient, sms);
            mySms.setMessageEncoding(CMessage.MessageEncoding.Enc7Bit);
            mySms.setStatusReport(true);
            mySms.setFlashSms(false);
            mySms.setValidityPeriod(1);
            cService.sendMessage(mySms);
            if (mySms.getStatusReport()) {
//                test = true;
//                toggle.append("\n:::- Message envoyé à : " + recipient + "");
//                toggle.append("\n:::- Texte : " + sms + "\n");
                return true;
            } else {
//                toggle.append("\n:::- Message non envoyé vérifier votre solde ou problème du réseau.");
            }
        } catch (Exception e) {
//            error.append("\n:::- Erreur lors de l'envoie du message");
        } finally {
//            ToggleText();
        }
        return false;
    }

    static Task<Void> incret;
    static Task<Void> getMessage;

    static void increment(String P_reseau) {
        System.out.println("-> Voila 5");
        incret = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        switch (P_reseau) {
                            case "airtel":
                                BouncaAnnimet(ext_Airtel);
                                ext_Airtel.setText(Events_Instance().formatNumber(Count().getAirtel()));
                                break;
                            case "orange":
                                BouncaAnnimet(ext_Orange);
                                ext_Orange.setText(Events_Instance().formatNumber(Count().getOrange()));
                                break;
                            case "vodacom":
                                Count().setVodacom();
                                BouncaAnnimet(ext_Vodacom);
                                ext_Vodacom.setText(Events_Instance().formatNumber(Count().getVodacom()));
                                break;
                            default:
                                System.out.println("eh bah..");
                        }
                    }
                });
                return null;
            }
        };
        new Thread(incret).start();
    }

//      Methode d'ecoute SMS Retrait 
    static int index;
    public static List<Retrait> Transit = new ArrayList();
    public static List<Retrait> retraits = new ArrayList();
    static String mssgSend = null;

    public static boolean ReraitAirtel(String number, float montant, String psswd) {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("#1 Retrait: chargement....\n");
//            Transit = retraitInstence().getRetraitClient();
                        System.out.println("#2 Retrait: Ajout....\n");
//                    mssgSend = "CASH " + retraits.get(index) + " " + retraits.get(index) + " " + donnee.getPasseMoney();
                        mssgSend = "CASH " + number + " " + montant + " " + psswd;
                        System.out.println("Message: " + mssgSend);
//                    BasculRetrait.append("\n#>> Nouveau Demande d'Envoie reçu traitement en cours ...");
//                    BasculRetrait.append("\n#>>Texte : " + msgSend);
                        SendMoney("+436", mssgSend, airtelService);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            });
//            System.out.println("size: " + retraitActivity.getSize());
//            NumeroTosend.add("+243999026241");

//                ToggleTextRetraitMoney();
        } catch (Exception e) {
            System.out.println("c'est moi");
            e.printStackTrace();
        }
        return false;
    }

    static Calendar cal;
    static String date;
    static int y, m, d;
    static Date dte = new Date();

    static String initDate() {
        cal = new GregorianCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH) + 1;
        y = cal.get(Calendar.YEAR);
        return date = y + "-" + m + "-" + d;
    }
}
