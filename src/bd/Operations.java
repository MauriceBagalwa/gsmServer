/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static bd.Connexion.Disconnect;
import static contro_traitement.Waitloading_simpleController.content_waitingSimple;
import static contro_traitement.Waitloading_simpleController.image_waitingSimple;
import static controllers.DonneeController.getData;
import static controllers.MainController._stckaMain;
import static controllers.MainController.initDash;
import static controllers.MainController.isParametre;
import static controllers.MainController.transactions;
import helpers.Events;
import static helpers.SharedPreference.Preferences;
import helpers.Traitement;
import static helpers.Variables.getVar_instance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import model.Retrait;
import models.Chartdata;
import models.Count;
import models.DashDetails;
import models.Message;
import models.Money;
import model.Transation;

/**
 *
 * @author smartTicket
 */
public class Operations {

    /**
     *
     * @Save Message
     */
    PreparedStatement prepare;
    ResultSet rs;
    public static Operations oper;
    public List list = new ArrayList();

    public static Operations Operation() {
        if (oper == null) {
            oper = new Operations();
        }
        return oper;
    }

    /**
     *
     * @param message
     * @return
     * @throws java.lang.Exception
     * @Save Message
     */
    public boolean saveSMS(Message message) throws Exception {
        try {
            prepare = Connexion.ConnectToDB().prepareCall("CALL SP_MESSAGE (?,?,?)");
            prepare.setString(1, message.getNumero());
            prepare.setString(2, message.getResau());
            prepare.setString(3, message.getMessage());
            prepare.execute();
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     *
     * @param money
     * @return
     * @throws java.lang.Exception
     * @Save Message
     */
    float mt = 0;

    public boolean saveMoney(Money money) throws Exception {
        try {
            System.out.println("Money: " + money.toString());
            prepare = Connexion.ConnectToDB().prepareCall("CALL SP_MONEY (?,?,?,?,?,?,?)");
            prepare.setString(1, money.getReseau());
            prepare.setString(2, money.getReference());
            prepare.setString(3, money.getNumero());
            prepare.setFloat(4, money.getDevise().equals("USD") ? money.getMontant() : (money.getMontant() / Preferences().getTaux()));
            prepare.setString(5, money.getDevise());
            prepare.setFloat(6, money.getSolde());
            prepare.setString(7, money.getSms());
            prepare.execute();
            return true;
        } catch (Exception e) {
            System.out.println("-#- Conflit de donnée...");
            return false;
//            throw new Exception(e);
        }
    }

    /**
     *
     * @Save Message
     */
    float getMontant(String devise, float montant) {
        return "CDF".equals(devise) ? montant : montant / Preferences().getTaux();
    }

    /**
     *
     * @param reseau
     * @param date
     * @return
     * @throws java.lang.Exception
     * @Save Message
     */
    List<Money> detailsMoney = new ArrayList<>();
    public static List<Money> MessageAirtelMoney = new ArrayList<>();
    public static List<Money> MessageOrangeMoney = new ArrayList<>();
    public static List<Money> moneyDetails = new ArrayList<>();
    public static List<Money> allInfo = new ArrayList<>();
    public static List<Chartdata> chartaInfo = new ArrayList<>();
    Money money;
//    static Transaction transaction;

    public List<Money> getDetails(String reseau, String date) throws Exception {
        try {
            System.out.println("data " + reseau.toLowerCase() + " date: " + date);
            date = "2020-11-1";
            rs = Connexion.ConnectToDB().prepareCall("CALL GETDETAILS ('" + reseau.toLowerCase() + "','" + date + "')").executeQuery();
            detailsMoney.clear();
            while (rs.next()) {
                money = new Money(rs.getString(1), rs.getFloat(2), rs.getString(3),
                        rs.getFloat(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getBoolean(8));
                detailsMoney.add(money);
            }
            System.out.println("size liste -> " + detailsMoney.size());
            return detailsMoney;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            Disconnect();
        }
    }

    public List<Money> getMessage(List<Money> list, Money money) throws Exception {
        list.add(money);
        if (isParametre.equals("traitement")) {

        }
        return list;
    }
    /**
     *
     * @Save Message
     */
    String sql;
    int _count;

    public Integer getCount(String reseau) throws Exception {
        try {

            _count = 0;
            sql = "SELECT COALESCE(count(reference),0) as 'count'"
                    + "from money where reseau='" + reseau + "' and cast(`date` as date)=curdate() ";
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            detailsMoney.clear();
            list.clear();
            if (rs.next()) {
                _count = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            Disconnect();
        }
        return _count;

    }

    /**
     *
     * @param sql
     * @return
     * @throws java.lang.Exception
     * @Save Message
     */
    public Integer Other_getCount(String sql) throws Exception {
        try {
            _count = 0;
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            if (rs.next()) {
                _count = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            Disconnect();
        }
        return _count;

    }
    /**
     *
     * @Save Message
     */
    String[] reseau = {"airtel", "orange", "vodacom"};
    Chartdata chartData;
    Count count;

    public List<Chartdata> getcharData(String date, int index) throws Exception {
        chartaInfo.clear();
        for (String string : reseau) {
            sql = "call PS_CHARTDATA2('" + string + "','" + date + "'," + index + ")";
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            if (rs.next()) {
                count = new Count(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
            }
            System.out.println("Count: " + count.toString());
            chartData = new Chartdata(string, count);
            chartaInfo.add(chartData);
        }
        return chartaInfo;

    }
    /**
     *
     * @Save Message
     */
    List<Money> listmessage = new ArrayList<>();
    List<Transation> trans = new ArrayList<>();
    List<Message> message = new ArrayList<>();
    List<Message> Lastmessage = new ArrayList<>();
    Message mssg;

    public List<Money> getMessage_En_Attente(String sql) throws Exception {
        try {
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            listmessage.clear();
            while (rs.next()) {
                money = new Money(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat("montant"), rs.getString(5), rs.getFloat(6), rs.getString(7));
                listmessage.add(money);
            }
        } catch (Exception e) {
        } finally {
            Disconnect();
        }
        return listmessage;
    }

    /**
     *
     * @param sql
     * @return
     * @throws java.lang.Exception
     * @Save Message
     */
    Transation transation;

    public List<Transation> Transactions(String sql) throws Exception {
        try {
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            trans.clear();
            while (rs.next()) {
                transation = new Transation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                trans.add(transation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Disconnect();
        }
        System.out.println("#I'm: " + trans.size() + "\n");
        return trans;
    }

    /**
     *
     * @param reference
     * @param code
     * @param i
     * @throws java.lang.Exception
     * @Save Message
     */
    public void changeEtat(String reference, String code) throws Exception {
        _count = 0;
        try {
//            sql = "update money set etat=true,codereference='" + code + "' where reference='" + reference + "'";
            if (code.equals("0")) {
                sql = "update money set etat=true where reference='" + reference + "'";
            } else {
                sql = "update money set etat=true,codereference='UTILISER' where reference='" + reference + "'";
            }
            System.out.println("request: " + sql);
            prepare = Connexion.ConnectToDB().prepareStatement(sql);
            prepare.execute();

//            transactions.remove(i);
            if (isParametre.equals("donnees")) {
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
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        } finally {
            Disconnect();
        }
    }

    public boolean Retrait_(Retrait body) throws Exception {
        try {
            sql = "insert into  retrait values(?,?,?,?,?,?,?,?,?,now())";
            prepare = Connexion.ConnectToDB().prepareStatement(sql);
            prepare.setString(1, body.getId());
            prepare.setString(2, body.getMotif());
            prepare.setFloat(3, body.getMontant());
            prepare.setString(4, body.getNumero());
            prepare.setString(5, body.getOperateur());
            prepare.setString(6, body.getDateEn());
            prepare.setInt(7, body.getRefCompte());
//            prepare.setBoolean(8, body.isFinished());
            prepare.setString(9, body.getNomAgent());
            prepare.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            getVar_instance().Error_Dialog("Erreur d'enregistrement.", e.getMessage());
        } finally {
            Disconnect();
        }
        return false;
    }

    Retrait retrait;
    List<Retrait> retraitList;

    public List<Retrait> getRetrait() throws Exception {
        try {
            rs = Connexion.ConnectToDB().prepareStatement("select * from `retrait`").executeQuery();
            retraitList.clear();
            while (rs.next()) {
//                retrait = new Retrait(rs.getString(1), rs.getNString(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getString(6),
//                        rs.getInt(7), rs.getBoolean(8), rs.getString(9), rs.getString(10));
//                trans.add(transation);
            }
            Events.dialog.close();
        } catch (Exception e) {
            image_waitingSimple.setVisible(false);
            content_waitingSimple.setVisible(true);
            _stckaMain.setDisable(false);
        }
        return retraitList;
    }

    /**
     *
     * @param sql
     * @return
     * @throws java.lang.Exception
     * @Save Message
     */
    public List<Message> messageOfday(String sql) throws Exception {
        try {
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            message.clear();
            while (rs.next()) {
                mssg = new Message(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                message.add(mssg);
            }
            System.out.println(message.toString());
        } catch (Exception e) {
        } finally {
            Disconnect();
        }
        return message;
    }

    public List<Message> LastmessageOfday(String sql) throws Exception {
        try {
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            Lastmessage.clear();
            while (rs.next()) {
                mssg = new Message(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                Lastmessage.add(mssg);
            }
        } catch (Exception e) {
        } finally {
            Disconnect();
        }
        return Lastmessage;
    }

    /**
     *
     * @param sql
     * @return
     * @throws java.lang.Exception
     * @Save Message
     */
    public List<Money> detailsMoney(String sql) throws Exception {
        try {
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            moneyDetails.clear();
            while (rs.next()) {
                money = new Money(rs.getString(1), rs.getFloat(2));
                moneyDetails.add(money);
            }
            System.out.println(message.toString());
        } catch (Exception e) {
        } finally {
            Disconnect();
        }
        return moneyDetails;
    }

    /**
     *
     * @param sql
     * @return
     * @throws java.lang.Exception
     * @Save Message
     */
//    get Allinfo of Money
    public List<Money> AllMoney(String sql) throws Exception {
        try {
            rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
            allInfo.clear();
            while (rs.next()) {
                money = new Money(rs.getString(1), rs.getFloat("montant"), rs.getString(3), rs.getFloat(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getString(10));
                allInfo.add(money);
            }
            System.out.println(allInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Disconnect();
        }
        return allInfo;
    }

    /**
     *
     * @param str
     * @Save Message
     */
    public void searhMessage(String str) {

    }

    /**
     *
     * @throws java.lang.Exception
     * @Save Message
     */
    public static List<DashDetails> dashDetails = new ArrayList<>();
    DashDetails dash;

    public void getDetailsServer() throws Exception {
        try {
            dashDetails.clear();
            for (String string : reseau) {
                sql = "call PS_DASH('" + string + "')";
                rs = Connexion.ConnectToDB().prepareStatement(sql).executeQuery();
                if (rs.next()) {
                    dash = new DashDetails(string, rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                }
                dashDetails.add(dash);
            }
            System.out.println("Details: " + dashDetails.toString());
            initDash = true;
        } catch (Exception e) {
//            System.out.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        transaction = new Transaction("P14GH0689VT45", "Airtel", "243999026241", (float) 2.5, "USD", (float) 45.7, "Trans. ID:IP14GH0689VT45 Vous avez recu 2.5 USD de 999026241 Bin.", false);
//        Operation().changeEtat(transaction.getReference());
    }
}
