/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import static contro_donnee.WithdateController.dateFlitre;

/**
 *
 * @author smartTicket
 */
public class rooters {

    /**
     * @package Gui
     */
    public static final String MAIN_ROUTER = "/gui/main.fxml";
    public static final String DASHBORD = "/gui/dashbord.fxml";
    public static final String SERVEUR = "/gui/serveur.fxml";
    public static final String TRAITEMENT = "/gui/traitement.fxml";
    public static final String DATA = "/gui/donnee_.fxml";
    public static final String RETRAIT = "/gui/retrait.fxml";
    public static final String PARAMETRES_ROUTER = "/gui/parametre.fxml";
    /**
     * @package Paramtres
     */
    public static final String MODEM_PARAMETRE = "/parametres/modem.fxml";
    public static final String BD_PARAMETRE = "/parametres/bd.fxml";
    public static final String CONFIG_MODEM_PARAMETRE = "/parametres/configModem.fxml";
    public static final String ESPACE_USER = "/parametres/user.fxml";
    /**
     * @package Traitement
     */
    public static final String DETAILSERVER = "/traitement/server.fxml";
    public static final String LOADINGMESSAGE = "/traitement/loading.fxml";
    public static final String SERVER_VUE = "/traitement/server_vue.fxml";
    public static final String VUE_TRANSACTION = "/traitement/transaction.fxml";
    public static final String EN_ATTENTE = "/traitement/en_attente.fxml";
    public static final String EN_RECEPTION = "/traitement/reception.fxml";
    public static final String INFO = "/traitement/info.fxml";
    public static final String TRAITEMENT_EN_ATTENTE = "/traitement/attente.fxml";
    public static final String LASTMESSAGE = "/traitement/lastmessage.fxml";
    public static final String MESSAGE_RECEV = "/traitement/message.fxml";
    public static final String RETRAIT_LOAD = "/traitement/retrait.fxml";
    public static final String MONEY_TRAITER_DEATILS = "/traitement/moneytraiter.fxml";
    /**
     * @Requetes
     */
    public static final String SQL_ALLMESSAGE = "select count(`id`) FROM `message` where "
            + "cast(`dateReception` as date)=CURdate()";
    public static final String SQL_EN_ATTENTE = "select  `numero`,`reference`, `montant`,`devise`, `reseau`, "
            + "`solde`, `message`"
            + " FROM `money` where etat=false order by date desc";
    public static final String SQL_FOR_CODE = "select `numero`,`reference`, `montant`,`devise`, `reseau`, `solde`, `message` FROM `money` "
            + "where etat=true and codereference='en_attente' order by date";
    public static final String SQL_MESSAGES_OFDAY = "select `dateReception`,`numero`, `reseau`, `message`"
            + " FROM `message` WHERE cast(`dateReception` as date)=curdate() order by dateReception desc";
    public static final String SQL_ALL_MESSAGE = "select `dateReception`,`numero`, `reseau`, `message` "
            + "FROM `message` order by dateReception desc";
    public static final String ALLMONEY = "SELECT reseau,sum(`montant`)  FROM `money` GROUP by reseau";

    public static final String MONEY_OF_DAY = "SELECT reseau,sum(`montant`) FROM `money` WHERE "
            + "cast(`date` as date)=curdate() GROUP by reseau";
    public static final String MONEY_TRAITER_ALL = "select `reference`, `montant`, `numero`,`solde`, `devise`,`reseau`, "
            + "`message`, `date`,"
            + " `etat`,`codereference` FROM `money` order by `date` desc";
    public static final String MONEY_TRAITER_OF_DAY = "select `reference`, `montant`, `numero`,`solde`, `devise`,`reseau`, "
            + " `message`, `date`, `etat`,`codereference`"
            + " FROM `money` where cast(`date` as date)=curdate() order by `date` desc";

//    public static final String sql1 = "select count(id) from message where reseau='orange'";
//    public static final String sql2 = "select count(REFERENCE) from money where reseau='orange'";
//    public static final String sql3 = "select count(REFERENCE) from money where reseau='orange' and etat=true";
//    public static final String sql4 = "select count(REFERENCE) from money where reseau='orange' and etat=false";
    /**
     * @Requetes
     */
    public static final String FLITRE_TODAY = "/donnee/today.fxml";
    public static final String FLITRE_WITHDATE = "/donnee/withdate.fxml";
    public static final String FLITRE_ALL = "/donnee/all.fxml";

    public static final String WAITLOADING_SIMPLE = "/traitement/waitloading_simple.fxml";
    public static final String DETAILS_RETRAIT = "/traitement/details_retrait.fxml";
}
