package com.example.projektrent;

import com.example.projektrent.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class GlavniEkranController {

    public void showStartScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("glavniEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        HelloApplication.getMainStage().setTitle("Dobro dosli dragi gosti!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

}
