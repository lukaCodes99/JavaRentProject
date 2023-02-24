package com.example.projektrent;

import com.example.projektrent.niti.IstekRegistracijeThread;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class IzbornikEkranController {

    public void showHatchbackScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hatchbackEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        scene.getStylesheets().add(HelloApplication.class.getResource("hatchbackStyle.css").toExternalForm());

        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showKombiScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("kombiEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);


        scene.getStylesheets().add(HelloApplication.class.getResource("hatchbackStyle.css").toExternalForm());



        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showLimuzinaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("limuzinaEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        scene.getStylesheets().add(HelloApplication.class.getResource("hatchbackStyle.css").toExternalForm());



        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }


}
