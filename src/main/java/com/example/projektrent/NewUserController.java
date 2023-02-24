package com.example.projektrent;

import com.example.projektrent.entiteti.Rola;
import com.example.projektrent.entiteti.VrstaGoriva;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class NewUserController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox<Rola> roleComboBox;

    private static final String USER_FILE = "users.txt";

    public void initialize() {
        roleComboBox.getItems().addAll(Rola.values());
    }

    @FXML
    public void addUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Rola role = roleComboBox.getValue();

        if (username.isEmpty() || password.isEmpty() || role == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Spremanje korisnika");
            alert.setHeaderText("Niste uspjeli unijeti novog korisnika!");
            alert.setContentText("Potrebno je ispuniti sva polja za unos kako bi se pravilno spremio novi korisnik");

            alert.showAndWait();
            return;
        }

        else{
            File log = new File("dat/users.txt");
            try{
                FileWriter fileWriter = new FileWriter(log, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                writer.write("\n"+role);
                writer.write("\n"+username);
                writer.write("\n"+password);

                writer.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Spremanje korisnika");
                alert.setHeaderText("Uspješno dodan novi korisnik!");
                alert.setContentText("korisnik " + username + " je uspjesno dodan u aplikaciju");
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
