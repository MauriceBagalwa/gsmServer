/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static helpers.SharedPreference.Preferences;
import static helpers.Variables.getVar_instance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

/**
 *
 * @author Dan Baruka
 */
public class Connexion {

    private static Connection connexion = null;

    public static Connection ConnectToDB() throws Exception {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            return connexion = DriverManager.getConnection("jdbc:mysql://" + Preferences().getHostname() + "/" + Preferences().getDatabase(), Preferences().getUser(), Preferences().getPassword());
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            getVar_instance().Error_Dialog("Erreur du serveur.", "Erreur liée à l'instance de MySql server...");
            throw new Exception("Erreur liée à l'instance de MySql server " + ex.getMessage());
        }
    }

    public static boolean isDisconnexionected(String server, String bd, String user, String psswd) throws Exception {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            connexion = DriverManager.getConnection("jdbc:mysql://" + server + "/" + bd, user, psswd);
            return true;
        } catch (Exception e) {
            throw new Exception("Erreur liée à l'instance de MySql server");
        } finally {
        }
    }

    public static boolean Disconnect() throws Exception {
        try {
            if (!connexion.isClosed()) {
                connexion.close();
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
//        ConnectToDB();
        Date today = new Date();
        String sortie = "jeudi 45 mars 2021";
//        DateFormat da = DateFormat.getDateInstance(DateFormat.FULL);
//        System.out.println("#" + da.format(today));
        String a = sortie.substring(0, sortie.indexOf(" ") + 3);
        String jour = sortie.substring(sortie.trim().indexOf(" ") + 2, sortie.length());
        System.out.println("#Jour: " + a.trim() + "\n#Mois: " + jour);
    }

}

//public class BDConnexion {
//
//  void findPrefs() {
//    Preferences prefs = Preferences.userRoot();
//  }
//
//  public static void main(String[] args) {
//    BDConnexion pw = new BDConnexion();
//    pw.findPrefs();
//  }
//
//}
