/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import static contro_traitement.LoadingController.moneyLoading;
import static helpers.rooters.TRAITEMENT_EN_ATTENTE;
import static contro_traitement.AttenteController.moneyEn_attente;
import static contro_traitement.LastmessageController.lastMessage;
import static contro_traitement.MessageController.messageRecev;
import static contro_traitement.MoneytraiterController.moneyTraiter;
import static contro_traitement.ServerController.*;
import static controllers.MainController.ListDetailserver;
import static controllers.TraitementController.sizeServer_;
import static controllers.TraitementController.vbx_;
import static helpers.Variables.listretrait_json;
import static helpers.rooters.DETAILSERVER;
import static helpers.rooters.LASTMESSAGE;
import static helpers.rooters.MESSAGE_RECEV;
import static helpers.rooters.RETRAIT_LOAD;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Retrait;
import models.Message;
import models.Money;

/**
 *
 * @author smartTicket
 */
public class Loading {

    public static Loading lod;

    public static Loading Loading() {
        if (lod == null) {
            lod = new Loading();
        }
        return lod;
    }
    /**
     *
     * @author smartTicket
     */
    Node[] node;

    public void initServerActif(int etat, int index) throws IOException {
        size = ListDetailserver.size();
        node = new Node[size];
        sizeServer_.setText("" + size);
        if (size > 0) {
            vbx_.getChildren().clear();
            for (i = 0; i < size; i++) {
                detailServer = ListDetailserver.get(i);
                indexList = i;
                node[i] = FXMLLoader.load(getClass().getResource(DETAILSERVER));
                if (etat == 0 && i == 0) {
                    pan_.setStyle("-fx-background-color:#F3F7FE");
                } else if (index == i) {
                    pan_.setStyle("-fx-background-color: #F3F7FE");
                }
                vbx_.getChildren().add(node[i]);
            }
            vbx_.setSpacing(15);
        }
    }

    /**
     *
     * @author smartTicket
     */
    public  int size, i, index = 0;
    static int afterAdd;

//    public static void loaddetails(List<Money> list) {
//        System.out.println("Test 2");
//        size = list.size();
//        if (size < 10) {
//            total_.setText("0" + size);
//        } else {
//            total_.setText("" + size);
//        }
//    }
    /**
     *
     * @author smartTicket
     * @param vbx
     * @param offset
     * @param limit
     * @param url
     * @throws java.io.IOException
     */
    HBox hbx1;
    HBox hbx2;
//

    public void ScrollwithVbx(VBox vbx, int data, int offset, int limit, int children, String url) throws IOException {
        node = new Node[limit + 1];
        i = 0;
        vbx.getChildren().clear();
        System.out.println("for i=" + offset + ";i<" + limit + ";i++");
//        for (index = offset; index <= limit;) {
//0;0<3;index++
        for (index = offset; index <= limit;) {
            afterAdd = limit - data;
            if ((afterAdd >= data)) {
                hbx1 = new HBox();
                for (int j = 0; j < limit; j++) {
                    node[j] = FXMLLoader.load(getClass().getResource(url));
                    hbx1 = new HBox();
                    hbx1.getChildren().add(node[j]);
                    index++;
                }
                vbx.getChildren().add(hbx1);
            } else {
                hbx2 = new HBox();
                for (int j = offset; j < afterAdd; j++) {
                    node[j] = FXMLLoader.load(getClass().getResource(url));

                    hbx2.getChildren().add(node[j]);
                    index++;
                }
                vbx.getChildren().add(hbx2);
            }

        }
    }

    public void selectedNode(int index) {
        for (i = 0; i < reste; i++) {
            if (i == index) {
                node[i].setStyle("-fx-background-color:#7F7F9C");
            } else {
                node[i].setStyle("-fx-background-color:transparent");
            }
        }
    }

    int a, sizeOp;

    public void ScrollOperation(VBox vbx, List<?> data, int offset, int limit, int children, String url) throws IOException {
        i = 0;
        /**
         * !3 Affichage du message (mssg)
         */
        vbx.getChildren().clear();
        System.out.println("for" + index + "=" + offset + ";" + index + "<" + limit);
        /**
         * !3 Affichage du message (mssg)
         */
        System.out.println("Data: " + data.size());
        a = data.size();
        System.out.println("A->..." + a);
        for (index = offset; index < limit;) {
            /**
             * !3 Affichage du message (mssg)
             */
            HBox hb1;
            HBox hb2;

            /**
             * !3 Affichage du message (mssg)
             */
            if (a - limit >= children) {
                System.out.println("#1 avec comme ");
                /**
                 * !3 Affichage du message (mssg)
                 */
                hb1 = new HBox();
                node = new Node[children];
                /**
                 * !3 Affichage du message (mssg)
                 */
                System.out.println("Add plus 2..." + a);

                for (int j = 0; j < children; j++) {
                    if (data instanceof Money) {
                        moneyLoading = (Money) data.get(index);
                    } else if (data instanceof Message) {
                        messageRecev = (Message) data.get(index);
                    }

                    node[j] = node[j] = FXMLLoader.load(getClass().getResource(url));
                    hb1.getChildren().add(node[j]);
                    index++;
                }
                /**
                 * !3 Affichage du message (mssg)
                 */
                hb1.setSpacing(3);
                vbx.getChildren().add(hb1);
                a = a - children;
            } else {
                System.out.println("valeur de A: " + a);
                /**
                 * !3 Affichage du message (mssg)
                 */
                hb2 = new HBox();
                node = new Node[a];
                /**
                 * !3 Affichage du message (mssg)
                 */
//                System.out.println("#2 " + limit);
//                System.out.println("#3 j=" + offset + " j<" + a);
                if (a >= children) {
                    System.out.println("#2->1 j=" + 0 + "j<" + children);
                    for (int j = 0; j < children; j++) {
                        if (data instanceof Money) {
                            moneyLoading = (Money) data.get(index);
                        } else if (data instanceof Message) {
                            messageRecev = (Message) data.get(index);
                        }
                        node[j] = FXMLLoader.load(getClass().getResource(url));
                        hb2.getChildren().add(node[j]);
                        index++;
                        hb2.setSpacing(4);
                    }
                    a = a - children;
                    vbx.getChildren().add(hb2);
                } else if (a > 0) {
//                    System.out.println("#2->2 j=" + 0 + "j<" + a);
                    for (int j = 0; j < a; j++) {
                        if (data instanceof Money) {
                            moneyLoading = (Money) data.get(index);
                        } else if (data instanceof Message) {
                            messageRecev = (Message) data.get(index);
                        }
                        node[j] = FXMLLoader.load(getClass().getResource(url));
                        hb2.getChildren().add(node[j]);
                        hb2.setSpacing(4);
                        index++;
                    }
                    a = 0;
                    vbx.getChildren().add(hb2);
                } else {
                    break;
                }
            }
            /**
             * !3 Affichage du message (mssg)
             */

//            }
//            }
            vbx.setSpacing(5);
        }
    }
    int reste;

    public void ScrollOperation(VBox vbx, List data, int offset, int limit, String url) throws IOException {
        try {
            vbx.getChildren().clear();
            size = data.size();
            i = 0;
            System.out.println("Size: " + size + "<" + limit);
            reste = (size < limit) ? size : limit;
            node = new Node[reste];

            if (size > 0) {
//                if (size < limit) {
//                    for (index = offset; index < size; index++) {
//                        if (url.equals(MESSAGE_RECEV)) {
//                            messageRecev = (Message) data.get(index);
//                        } else if (url.equals(RETRAIT_LOAD)) {
//                            listretrait_json = (Retrait) data.get(index);
//                        } else {
//                            moneyTraiter = (Money) data.get(index);
//                        }
//
//                        node[i] = FXMLLoader.load(getClass().getResource(url));
//                        vbx.getChildren().add(node[i]);
//                    }
//                } else {
                for (index = offset; index < reste; index++) {
                    if (url.equals(MESSAGE_RECEV)) {
                        messageRecev = (Message) data.get(index);
                    } else if (url.equals(RETRAIT_LOAD)) {
                        listretrait_json = (Retrait) data.get(index);
                    } else {
                        moneyTraiter = (Money) data.get(index);
                    }
                    node[i] = FXMLLoader.load(getClass().getResource(url));
                    vbx.getChildren().add(node[i]);
                }
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer LoadHBX(HBox hbx, List<Money> data) throws IOException {

        node = new Node[data.size()];
        i = 0;
        hbx.getChildren().clear();
        for (Money money : data) {
            moneyEn_attente = money;
            node[i] = FXMLLoader.load(getClass().getResource(TRAITEMENT_EN_ATTENTE));
            hbx.getChildren().add(node[i]);
            i++;
            if (i == 5) {
                break;
            }
        }
        return data.size();
    }

    /**
     * !3 Affichage du message (mssg)
     *
     * @param vbx
     * @param data
     * @throws java.io.IOException
     */
    public void LoadVBX(VBox vbx, List<Message> data) throws IOException {
        try {
            if (data.size() > 0) {
                node = new Node[data.size()];
                i = 0;
                vbx.getChildren().clear();
                for (Message mssg : data) {
                    lastMessage = mssg;
                    node[i] = FXMLLoader.load(getClass().getResource(LASTMESSAGE));
//            BouncaAnnimet(node[i]);
                    vbx.getChildren().add(node[i]);

                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * variables...
     */
    ObservableList<Message> message_;
    public TableView<Message> tablevieuMssg;
    public TableColumn<Message, String> dateColumn = new TableColumn<>("NUMERO");
    public TableColumn<Message, String> numColum = new TableColumn<>("RESEAU");
    public TableColumn<Message, String> mssgColumn = new TableColumn<>("DATE");
    public TableColumn<Message, String> reseauColumn = new TableColumn<>("MESSAGE");

    public TableView<Message> getTable(List<Message> mssg) {
        /**
         * initialistion des colonne
         *
         * @date
         * @numero
         * @message
         */
        dateColumn.setMinWidth(50);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        numColum.setMinWidth(50);
        numColum.setCellValueFactory(new PropertyValueFactory<>("numero"));

        reseauColumn.setMinWidth(40);
        reseauColumn.setCellValueFactory(new PropertyValueFactory<>("resau"));

//        mssgColumn.setMinWidth(300);
        mssgColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        /**
         * Ajout dans la tableView
         */
        tablevieuMssg = new TableView<>();
        tablevieuMssg.setItems(getmessage(mssg));
        tablevieuMssg.getColumns().addAll(mssgColumn, dateColumn, numColum, reseauColumn);
        return tablevieuMssg;
    }

    public ObservableList<Message> getmessage(List<Message> mssg) {
        System.out.println("Message of day: " + mssg.size());
        message_ = FXCollections.observableArrayList();
        mssg.forEach(message -> {
            message_.add(message);
        });
        return message_;
    }
}
