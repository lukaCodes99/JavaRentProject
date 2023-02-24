package com.example.projektrent;

import com.example.projektrent.niti.IstekRegistracijeThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class AdminIzbornikController {
    public void newHatchbackScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newHatchback.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);




        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void newLimuzinaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newLimuzina.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);



        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void newKombiScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newKombi.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);




        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void newUserScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("newUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);




        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void hatchbackRegistracijaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hatcbackRegistracija.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);




        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showHatchbackScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hatchbackEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        //Parent root = fxmlLoader.load();
        scene.getStylesheets().add(HelloApplication.class.getResource("hatchbackStyle.css").toExternalForm());

        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showKombiScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("kombiEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        //Parent root = fxmlLoader.load();
        scene.getStylesheets().add(HelloApplication.class.getResource("hatchbackStyle.css").toExternalForm());




        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showLimuzinaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("limuzinaEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        //Parent root = fxmlLoader.load();
        scene.getStylesheets().add(HelloApplication.class.getResource("hatchbackStyle.css").toExternalForm());



        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }



}
