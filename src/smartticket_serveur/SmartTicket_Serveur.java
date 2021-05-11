/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartticket_serveur;

import static controllers.MainController.harrow;
import static controllers.MainController.taskdash;
import static helpers.Traitement.Airtel;
import static helpers.Traitement.Orange;
import static helpers.Traitement.Vodacom;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author smartTicket
 */
public class SmartTicket_Serveur extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/main.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new javafx.scene.image.Image("/image/server.jpeg"));
        stage.show();
//        stage.setOnCloseRequest(e -> {
//            harrow.cancel(true);
//            taskdash.cancel();
//            if (Airtel != null) {
//                Airtel.cancel();
//            }
//            
//            if (Orange != null) {
//                Orange.cancel();
//            }
//            if (Vodacom != null) {
//                Vodacom.cancel();
//            }
//        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
