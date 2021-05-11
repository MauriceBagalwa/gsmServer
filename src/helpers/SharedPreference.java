/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.prefs.Preferences;

/**
 *
 * @author smartTicket
 */
public class SharedPreference {

    private final Preferences prefs = Preferences.userNodeForPackage(SharedPreference.class);
    static SharedPreference preferences;

    public static SharedPreference Preferences() {
        if (preferences == null) {
            preferences = new SharedPreference();
        }
        return preferences;
    }

    public void InitDataBase(String hostname, String bdname, String user, String psswd) {
        setHostname(hostname);
        setDatabase(bdname);
        setUser(user);
        setPassword(psswd);
    }

    public void InitAirtelConfig(String port, String modem, String centreMssg, int bitTemmp, String pinSim) {
        setPort_Airtel(port);
        setModem_Airtel(modem);
        setCentreMessagerie_Airtel(centreMssg);
        setBitTemporisation_Airtel(bitTemmp);
    }

    public void InitOrange(String port, String modem, String centreMssg, int bitTemmp, String pinSim) {
        setPort_Orange(port);
        setModem_Orange(modem);
        setCentreMessagerie_Orange(centreMssg);
        setBitTemporisation_Orange(bitTemmp);
    }

    public void InitVodacom(String port, String modem, String centreMssg, int bitTemmp, String pinSim) {
        setPort_Vodacom(port);
        setModem_Vodacom(modem);
        setCentreMessagerie_Vodacom(centreMssg);
        setBitTemporisation_Vodacom(bitTemmp);
    }

    /**
     * @return @Base de Données
     */
    public String getHostname() {
        return this.prefs.get("hostname", "localhost");
    }

    public void setHostname(String _server) {
        this.prefs.put("hostname", _server);
    }

    public String getUser() {
        return this.prefs.get("user", "root");
    }

    public void setUser(String _user) {
        this.prefs.put("user", _user);
    }

    public String getPassword() {
        return this.prefs.get("password", "");
    }

    public void setPassword(String _password) {
        this.prefs.put("password", _password);
    }

    public String getDatabase() {
        return this.prefs.get("database", "null");
    }

    public void setDatabase(String _database) {
        this.prefs.put("database", _database);
    }

    /**
     *
     * @return @Messagerie Airtel
     */
    public String getPort_Airtel() {
        return this.prefs.get("portAirtel", "null");
    }

    public void setPort_Airtel(String port) {
        this.prefs.put("portAirtel", port);
    }

    public String getModem_Airtel() {
        return this.prefs.get("modemAirtel", "null");
    }

    public void setModem_Airtel(String _modem) {
        this.prefs.put("modemAirtel", _modem);
    }

    public String getCentreMessagerie_Airtel() {
        return this.prefs.get("CentreMessagerieAirtel", "null");
    }

    public void setCentreMessagerie_Airtel(String numeroCentreMessagerie) {
        this.prefs.put("CentreMessagerieAirtel", numeroCentreMessagerie);
    }

    public int getBitTemporisation_Airtel() {
        return this.prefs.getInt("bitTemporisationAirtel", 9600);
    }

    public void setBitTemporisation_Airtel(int bitTemporisation) {
        this.prefs.putInt("bitTemporisationAirtel", bitTemporisation);
    }

    public String getPinSIM_Airtel() {
        return this.prefs.get("pinAirtel", "1234");
    }

    public void setPinSIM_Airtel(String pin) {
        this.prefs.put("pinAirtel", pin);
    }

    /**
     *
     * @return @Messagerie Vodacom
     */
    public String getPort_Vodacom() {
        return this.prefs.get("portVodacom", "null");
    }

    public void setPort_Vodacom(String port) {
        this.prefs.put("portVodacom", port);
    }

    public String getModem_Vodacom() {
        return this.prefs.get("modemVodacom", "null");
    }

    public void setModem_Vodacom(String _modem) {
        this.prefs.put("modemVodacom", _modem);
    }

    public String getCentreMessagerie_Vodacom() {
        return this.prefs.get("CentreMessagerieVodacom", "null");
    }

    public void setCentreMessagerie_Vodacom(String numeroCentreMessagerie) {
        this.prefs.put("CentreMessagerieVodacom", numeroCentreMessagerie);
    }

    public int getBitTemporisation_Vodacom() {
        return this.prefs.getInt("bitTemporisationVodacom", 9600);
    }

    public void setBitTemporisation_Vodacom(int bitTemporisation) {
        this.prefs.putInt("bitTemporisationVodacom", bitTemporisation);
    }

    public String getPinSIM_Vodacom() {
        return this.prefs.get("pinVodacom", "1234");
    }

    public void setPinSIM_Vodacom(String pin) {
        this.prefs.put("pinVodacom", pin);
    }

    /**
     *
     * @return @Messagerie Orange
     */
    public String getPort_Orange() {
        return this.prefs.get("portOrange", "null");
    }

    public void setPort_Orange(String port) {
        this.prefs.put("portOrange", port);
    }

    public String getModem_Orange() {
        return this.prefs.get("modemOrange", "null");
    }

    public void setModem_Orange(String _modem) {
        this.prefs.put("modemOrange", _modem);
    }

    public String getCentreMessagerie_Orange() {
        return this.prefs.get("CentreMessagerieOrange", "null");
    }

    public void setCentreMessagerie_Orange(String numeroCentreMessagerie) {
        this.prefs.put("CentreMessagerieOrange", numeroCentreMessagerie);
    }

    public int getBitTemporisation_Orange() {
        return this.prefs.getInt("bitTemporisationOrange", 9600);
    }

    public void setBitTemporisation_Orange(int bitTemporisation) {
        this.prefs.putInt("bitTemporisationOrange", bitTemporisation);
    }

    public String getPinSIM_Orange() {
        return this.prefs.get("pinOrange", "1234");
    }

    public void setPinSIM_Orange(String pin) {
        this.prefs.put("pinOrange", pin);
    }

    public float getTaux() {
        return this.prefs.getFloat("taux", 1500);
    }

    public void setTaux(float taux) {
        this.prefs.putFloat("taux", taux);
    }
}
