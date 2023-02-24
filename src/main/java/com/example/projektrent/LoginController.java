package com.example.projektrent;

import com.example.projektrent.entiteti.Rola;
import com.example.projektrent.entiteti.User;
import com.example.projektrent.entiteti.Vozilo;
import com.example.projektrent.iznimke.IstekRegistracijeException;
import com.example.projektrent.iznimke.KrajPrijePocetkaException;
import com.example.projektrent.iznimke.PredugaRezervacijaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginController {


    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    Map<String, User> users;

    public void initialize() {

        users = loadUsersFromFile();

        /*loginButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = String.valueOf(passwordField.toString());
            System.out.println("tu sam");
            try {
                onLoginButtonClick(username, password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });*/

    }

    public void onLoginButtonClick() throws IOException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();


        User user = users.get(username);
        System.out.println(password + " ovdje tu  \n");
        String hashed=  user.hashPassword(password);
        System.out.println(hashed);
        //loz i lozinka
        if (user.getUsername().equals(username) && user.validateLozinka(hashed)) {
            if (user.getRola() == Rola.ADMIN) {

                showAdminScreen();
            } else if (user.getRola() == Rola.USER) {

                System.out.println("tu smo");
                showUserScreen();
            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Prijava neuspjela");
            alert.setHeaderText("Pogrešno korisničko ime ili lozinka");
            alert.setContentText("Potrebno je unijeti točne podatke za prijavu");

            alert.showAndWait();

        }
    }
    public void showUserScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("glavniEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Dobro dosli dragi gosti!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void showAdminScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminEkran.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HelloApplication.getMainStage().setTitle("Dobro dosli dragi gosti!");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    private Map<String, User> loadUsersFromFile() {
        Map<String, User> korisnici=new HashMap<>();
        try(BufferedReader bufReaderKorisnika=new BufferedReader(new FileReader(new File("dat/users.txt")))) {

            List<String> datotekaKorinsici=bufReaderKorisnika.lines().toList();


            for (int i = 0; i < datotekaKorinsici.size(); i+=3) {
                String rola=datotekaKorinsici.get(i);
                String username=datotekaKorinsici.get(i+1);
                String lozinka=datotekaKorinsici.get(i+2);

                korisnici.put(username, new User(Rola.valueOf(rola), username, lozinka));
            }

        }catch (IOException ex){
            System.out.println("Dogodila se pogreškica");
        }
        return korisnici;
    }

    public boolean login(String username, char[] password) {
        User user = users.get(username);
        if (user == null) {
            return false;
        }
        return user.validateLozinka(password.toString());
    }

    public Rola getRole(String username) {
        User user = users.get(username);
        return user.getRola();
    }


}
