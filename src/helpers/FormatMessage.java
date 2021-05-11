/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import models.Money;

/**
 *
 * @author smartTicket
 */
public class FormatMessage {

    public static final String AIRTEL_MONEY = "Vous avez recu";
    public static final String M_PESA = "Argent recu de";
    static String reference, devise, numero, personne, str, str2;
    static float montant, solde;

    public static Money isAirtel(String mssg) {
        Money Money = null;
        try {
            System.out.println("Message: " + mssg);
//            if (mssg.substring(mssg.indexOf("Vous avez recu"), mssg.indexOf("recu") + 4).equals(AIRTEL_MONEY)) {
//            reference = mssg.trim().substring(mssg.trim().indexOf(':', 0) + 2, mssg.trim().indexOf("Vous avez", 0) - 1);
//            str = mssg.substring(mssg.indexOf("recu ") + 5);
//            montant = Float.parseFloat(str.substring(0, str.indexOf(" ")));
//            devise = mssg.substring(mssg.indexOf(" de") - 3, mssg.indexOf(" de"));
//            numero = "+243" + mssg.trim().substring(mssg.indexOf("de", 0) + 3, mssg.indexOf("de", 0) + 12);
//            if (mssg.contains("CDF")) {
//                solde = Float.parseFloat(mssg.trim().substring(mssg.lastIndexOf("de ") + 3, mssg.lastIndexOf("CDF") - 1));
//            } else {
//                solde = Float.parseFloat(mssg.trim().substring(mssg.lastIndexOf("de ") + 3, mssg.lastIndexOf("USD") - 1));
//            }
//            reference = mssg.trim().substring(mssg.trim().indexOf(':', 0) + 2, mssg.trim().indexOf("Vous avez", 0) - 1);
//            str = mssg.substring(mssg.indexOf("recu ") + 5);
//            montant = Float.parseFloat(str.substring(0, str.indexOf(" ")));
//            str = mssg.substring(mssg.indexOf(" a part") - 3);
//            devise = str.substring(0, str.indexOf(" "));
//            str = mssg.substring(mssg.indexOf("de ") + 3);
//            personne = str.substring(0, str.indexOf("."));
//            str = mssg.substring(mssg.indexOf("est ") + 4);
//            solde = Float.parseFloat(str.substring(0, str.indexOf(" ")));
            reference = mssg.trim().substring(mssg.trim().indexOf(':', 0) + 2, mssg.trim().indexOf("Vous avez", 0) - 1);
            str = mssg.substring(mssg.indexOf("recu ") + 5);
            montant = Float.parseFloat(str.substring(0, str.indexOf(" ")));
            //devise = mssg.substring(mssg.lastIndexOf(" ") + 1);
            devise = mssg.substring(mssg.lastIndexOf(" ") + 1, mssg.length() - 1);
            str = mssg.substring(mssg.indexOf("de ") + 3);
            numero = "+243" + str.substring(0, str.indexOf(" "));
            str = mssg.substring(mssg.lastIndexOf("de") + 3).trim();
            solde = Float.parseFloat(str.substring(0, str.indexOf(" ")));
            Money = new Money(reference, montant, numero, solde, devise, "Airtel", mssg);
//            }
        } catch (Exception e) {
        }
        return Money;
    }

    public static boolean isM_PESA(String mssg) {
        Money Money = null;
        reference = mssg.substring(mssg.indexOf("Reference: "));

        return mssg.substring(0, 11).equals(M_PESA);
    }

    public static Money model_OrangeMoney(String mssg) {
        Money Money = null;
        try {
//            reference = mssg.substring(mssg.indexOf("Ref: ") + 5);
//            str = mssg.substring(0, mssg.indexOf(" de") - 4);
//            montant = Float.parseFloat(str.substring(str.lastIndexOf(" ") + 1));
//            str = mssg.substring(mssg.indexOf("recu ") + 5);
//            devise = str.substring(str.indexOf(" ") + 1, str.indexOf(" de"));
//            str = mssg.substring(mssg.indexOf("de ") + 3, mssg.indexOf(". N"));
//            numero = "+243" + str.substring(str.indexOf(" ") + 2);
//            str = mssg.substring(mssg.indexOf(": ") + 2);
//            solde = Float.parseFloat(str.substring(0, str.indexOf(" ")));
            reference = mssg.substring(mssg.indexOf("Ref: ") + 5);
            str = mssg.substring(mssg.indexOf("de ") + 3);
            montant = Float.parseFloat(str.substring(0, str.indexOf(" ")));
            devise = str.substring(str.indexOf(" ") + 1, 5);
            str = mssg.substring(mssg.indexOf("par ") + 4);
            personne = str.substring(0, str.indexOf(" "));
            str = mssg.substring(mssg.indexOf(": ") + 2);
            solde = Float.parseFloat(str.substring(0, str.indexOf(" ")));
            Money = new Money(reference, montant, personne, solde, devise, "Orange", mssg);
        } catch (Exception e) {
        }
        return Money;
    }

    public static void main(String[] args) {

        System.out.println(isAirtel("Trans. ID: PP201114.2323.D52728 Vous avez recu 0.1000 USD de 973547424 baruka simwerayi. Votre solde est de 1.6500 USD."));
        System.out.println(isAirtel(str).toString());

    }
}
