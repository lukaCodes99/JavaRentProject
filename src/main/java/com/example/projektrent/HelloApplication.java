package com.example.projektrent;

import com.example.projektrent.niti.IstekRegistracijeThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {



        mainStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //stage.setTitle("Dobro dosli dragi gosti!");
        stage.setScene(scene);
       IstekRegistracijeThread hatchbackThread = new IstekRegistracijeThread("Hatchback", 1);
        IstekRegistracijeThread limuzinaThread = new IstekRegistracijeThread("Limuzina", 2);
        IstekRegistracijeThread kombiThread = new IstekRegistracijeThread("Kombi", 3);


        Thread hatchbackThreadObj = new Thread(hatchbackThread);
        Thread limuzinaThreadObj = new Thread(limuzinaThread);
        Thread kombiThreadObj = new Thread(kombiThread);

        hatchbackThreadObj.start();
        limuzinaThreadObj.start();
        kombiThreadObj.start();



        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getMainStage(){
        return mainStage;
    }

}