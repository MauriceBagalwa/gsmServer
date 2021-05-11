/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import static contro_parametre.ModemController.initDataText;
import static controllers.MainController.indexEmpty;
import static controllers.MainController._stckaMain;
import static controllers.MainController.isParametre;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author smartTicket
 */
public class Events {

    /**
     *
     * @Effet label apres selection
     */
    public static Events event;
    public static JFXDialog dialog = new JFXDialog();
    public Pane pans[];

    public static Events Events_Instance() {
        if (event == null) {
            event = new Events();
        }
        return event;
    }

    /**
     * @param color1
     * @param color2
     * @param lab
     * @Fonction qui permet de mettre en grand le boutton selectionner
     */
    public void seleteLab(String color1, String color2, Label... lab) {
        try {
            for (Label tr : lab) {
                tr.setStyle(null);
//                tr.setTextFill(Color.web("#a1a1a1"));
                tr.setTextFill(Color.web(color1));
            }
            lab[0].setTextFill(Color.web(color2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param location
     * @param pans
     * @Fonction qui permet de colorer l'icon
     */
    public void colorPan(String location, Pane... pans) {
        for (Pane pan : pans) {
            pan.getStylesheets().clear();
            pan.setStyle("-fx-background-color:transparent;");
        }

        switch (location) {
            case "main": {
                pans[0].getStylesheets().add(getClass().getResource("/styles/selector.css").toExternalForm());
//                pans[0].setStyle("-fx-background-color: #FFC36E;-fx-background-radius:4px;");
                pans[0].setStyle("-fx-background-color: #F6891F;-fx-background-radius:4px;");
                break;
            }
            case "parametre": {
                pans[0].getStylesheets().add(getClass().getResource("/styles/selectorConf.css").toExternalForm());
            }
        }
    }

    /**
     * @param node
     * @param url
     * @Fonction permetant de recevoir d'autre Fichier fxml
     */
    public void setContent(Node node, URL url) {
        try {
            Node child = FXMLLoader.load(url);
            if (node instanceof StackPane) {
                StackPane contain_area = (StackPane) node;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            } else if (node instanceof VBox) {
                VBox contain_area = (VBox) node;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            } else if (node instanceof HBox) {
                HBox contain_area = (HBox) node;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            } else if (node instanceof AnchorPane) {
                AnchorPane contain_area = (AnchorPane) node;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url
     * @param width
     * @param height
     * @throws java.io.IOException
     * @Fonction permetant de recevoir d'autre Fichier fxml
     */
    public void Dialog(String url, double width, double height) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource(url));
        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setPadding(Insets.EMPTY);
        dl.setBody(node);
        dl.setPrefWidth(width);
        dl.setPrefHeight(height);
        dialog = new JFXDialog(_stckaMain, dl, JFXDialog.DialogTransition.CENTER, false);
        dialog.setPrefSize(width, height);
        dialog.show(_stckaMain);
        dialog.setOnDialogClosed(ex -> {
            if (!"other".equals(isParametre)) {
                initDataText();
            }
        });
    }

    public void close_Dialog() {
        dialog.close();
    }

    /**
     * Methode de verification des champs.
     *
     * @param nodes
     * @return
     */
    public boolean isFieldsempty(Node... nodes) {
        int i = 0;
        for (Node node : nodes) {
            if (node instanceof TextField) {
                TextField pass = (TextField) node;
                if (pass.getText() == null || pass.getText().isEmpty()) {
                    break;
                }
                i++;
            } else if (node instanceof PasswordField) {
                PasswordField area = (PasswordField) node;
                if (area.getText() == null || area.getText().trim().isEmpty()) {
                    break;
                }
                i++;
            } else if (node instanceof TextArea) {
                TextArea area = (TextArea) node;
                if (area.getText() == null || area.getText().trim().isEmpty()) {
                    break;
                }
                i++;
            } else if (node instanceof Label) {
                Label txt = (Label) node;
                if (txt.getText() == null || txt.getText().isEmpty()) {
                    break;
                }
                i++;
            } else {
                Text text = (Text) node;
                if (text.getText().isEmpty()) {
                    break;
                }
                i++;
            }
        }
        indexEmpty = i;
        return i != nodes.length;
    }

    /**
     * Methode d'Initialisation.
     *
     * @param field
     */
    public void initFields(Object... field) {
        try {
            for (Object f : field) {
                if (f instanceof TextField) {
                    TextField text = (TextField) f;
                    text.setText(null);
                } else if (f instanceof DatePicker) {
                    DatePicker text = (DatePicker) f;
                    text.setValue(null);
                } else if (f instanceof TextArea) {
                    TextArea text = (TextArea) f;
                    text.setText(null);
                } else if (f instanceof ComboBox) {
                    ComboBox text = (ComboBox) f;
                    text.setValue(null);
                } else if (f instanceof Label) {
                    Label txt = (Label) f;
                    txt.setText(null);
                } else if (f instanceof PasswordField) {
                    PasswordField txt = (PasswordField) f;
                    txt.setText(null);
                }
            }
//                           
        } catch (Exception ex) {

        }

    }

    /**
     * Methode d'Initialisation.
     *
     * @param number
     * @return
     */
    public String formatNumber(int number) {
        return (number < 10) ? "0" + number : "" + number;

    }

    public String formatNumber(int number, String libele) {
        return (number < 10) ? "0" + number + " " + libele : "" + number + " " + libele;

    }

    public String formatNumberPlus(int number) {
        return (number < 5) ? "0" + number : "5+";

    }
    /**
     * Methode d'Initialisation.
     *
     * @param number
     * @return
     */
    static BounceIn bounce;

    public static void BouncaAnnimet(Label lab) {
        bounce = new BounceIn(lab);
        bounce.setCycleDuration(2);
        bounce.play();
    }
    static FadeIn fadeIn;

    public static void FadeInAnnimation(Text txt) {
        fadeIn = new FadeIn(txt);
        fadeIn.setCycleDuration(3);
        fadeIn.play();
    }

    public void ErrorPan(int index, AnchorPane... pans) {
        pans[index].setStyle("-fx-border-color:red;-fx-border-width:0.2;");
    }

    public void BorderColor(AnchorPane... pans) {
        for (AnchorPane pan : pans) {
            pan.setStyle("-fx-border-color: #0000005c;-fx-border-width:0.1;");
        }
    }
    /**
     * @2
     */
    static String style = "-fx-border-color: CCC;"
            + "-fx-border-witdh:0.2;"
            + "-fx-border-radius:50;"
            + "-fx-fill:#8794a4;"
            + "-fx-font-size:9;";
    static String styleDay = "-fx-border-color: CCC;"
            + "-fx-border-witdh:0.4;"
            + "-fx-border-radius:50;"
            + "-fx-fill:#193b68;"
            + "-fx-font-size:11;"
            + "-fx-background-color:F6891F;"
            + "-fx-backgound-radius:50";

    public static void initCountMssg_of_Day(int day, Label... lab) {
        for (Label lab1 : lab) {
            lab1.setStyle(style);
        }
        lab[day].getStyleClass().add("labDay");
    }

    /**
     *
     */
    static Task<Void> taskMssg;
    static MaterialDesignIconView icon;

    public void FormateMssg(Node content, boolean index, ImageView image, String mssg, Label lab, JFXButton bt) throws InterruptedException {

        taskMssg = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                Thread.sleep(2000);
                image.setVisible(false);
                bt.setVisible(true);
                if (index) {
                    icon = new MaterialDesignIconView(MaterialDesignIcon.EMOTICON);
                    icon.setStyle("-fx-fill: #007bbc");

                } else {
                    icon = new MaterialDesignIconView(MaterialDesignIcon.EMOTICON_SAD);
                    icon.setStyle("-fx-fill: #d04848");
                }
                icon.setSize("20");
                System.out.println("#2...");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            lab.setText(mssg);
                            lab.setGraphic(icon);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                System.out.println("#3...");
                Thread.sleep(2500);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lab.setGraphic(null);
                            lab.setText("");

                            content.setVisible(false);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                return null;
            }

        };
        new Thread(taskMssg).start();
    }
}
