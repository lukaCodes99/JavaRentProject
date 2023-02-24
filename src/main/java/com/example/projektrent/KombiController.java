package com.example.projektrent;

import com.example.projektrent.entiteti.Kombi;
import com.example.projektrent.entiteti.VrstaGoriva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KombiController {

    @FXML
    private TextField markaAutaTextField;
    @FXML
    private TextField modelAutaTextField;
    @FXML
    private ComboBox<VrstaGoriva> vrstaGorivaComboBox;
    @FXML
    private ComboBox<Integer> comboSnaga;
    @FXML
    private ComboBox<Integer> comboBrojSjedala;
    @FXML
    private TableView<Kombi> kombiTableView;
    @FXML
    private TableColumn<Kombi, String> markaColumn;
    @FXML
    private TableColumn<Kombi, String> modelColumn;
    @FXML
    private TableColumn<Kombi, String> vrstaGorivaColumn;
    @FXML
    private TableColumn<Kombi, String> snagaColumn;
    @FXML
    private TableColumn<Kombi, String> brojSjedalaColumn;
    @FXML
    private TableColumn<Kombi, String> IDColumn;
    @FXML
    private Button clearButton;
    @FXML
    private Button filterButton;
    List<Kombi> kombiVozila;

    public void initialize() {

        kombiVozila = BazaPodataka.dohvatiSvaKombiVozila();

        markaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarka()));

        //markaColumn.setCellValueFactory(new PropertyValueFactory<>("marka"));
        modelColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        vrstaGorivaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVrsta_goriva().toString()));
        snagaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSnaga().toString()));
        brojSjedalaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrojSjedala().toString()));
        IDColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID().toString()));

        kombiTableView.setItems(FXCollections.observableList(kombiVozila));

        vrstaGorivaComboBox.getItems().addAll(VrstaGoriva.values());
        comboSnaga.getItems().addAll(90, 110, 130);
        comboBrojSjedala.getItems().addAll(8, 10, 12);

        clearButton.setOnAction(event -> {
            vrstaGorivaComboBox.setValue(null);
            comboSnaga.setValue(null);
            comboBrojSjedala.setValue(null);
            markaAutaTextField.setText("");
            modelAutaTextField.setText("");
            filterVozila();
        });

        filterButton.setOnAction(event -> {
            filterVozila();
        });

        filterVozila();
    }

    public void filterVozila() {
        String markaAuta = markaAutaTextField.getText();
        String modelAuta = modelAutaTextField.getText();
        VrstaGoriva vrstaGoriva = vrstaGorivaComboBox.getValue();
        Integer snaga = comboSnaga.getValue();
        Integer brojSjedala = comboBrojSjedala.getValue();

        List<Kombi> filteredKombi = kombiVozila.stream()
                .filter(k -> (markaAuta.isEmpty() || k.getMarka().contains(markaAuta)))
                .filter(k -> (modelAuta.isEmpty() || k.getModel().contains(modelAuta)))
                .filter(k -> (vrstaGoriva == null || k.getVrsta_goriva().equals(vrstaGoriva)))
                .filter(k -> (snaga == null || k.getSnaga() > snaga))
                .filter(k -> (brojSjedala == null || k.getBrojSjedala() >= brojSjedala))
                .collect(Collectors.toList());

        kombiTableView.setItems(FXCollections.observableList(filteredKombi));
    }

    /*private List<Kombi>  ucitajListu(){
        List<Kombi> kombiVozila=new ArrayList<>();

        kombiVozila.add(new Kombi(1, "Mercedes", "Vito", 90, LocalDate.of(2022, 01, 01), VrstaGoriva.BENZIN, 7));
        kombiVozila.add(new Kombi(2, "Renault", "Trafic", 110, LocalDate.of(2021, 12, 01), VrstaGoriva.DIZEL, 9));
        kombiVozila.add(new Kombi(3, "Citroen", "Jumpy", 130, LocalDate.of(2022, 05, 01), VrstaGoriva.BENZIN, 7));
        kombiVozila.add(new Kombi(4, "Volkswagen", "Transporter", 90, LocalDate.of(2022, 02, 01), VrstaGoriva.DIZEL, 9));
        kombiVozila.add(new Kombi(5, "Ford", "Tourneo", 110, LocalDate.of(2021, 11, 01), VrstaGoriva.BENZIN, 7));
        return kombiVozila;
    }*/


    public void showRezervacijaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rezervacijaEkran.fxml"));
        Parent root1=(Parent) fxmlLoader.load();
        Stage stage=new Stage();

        //promijeniti stylesheet, mozda dodati prije nego sto se sve loada da se napravi neka animacija....
        //stage.getStylesheets().add(HelloApplication.class.getResource("hatchbackStyle.css").toExternalForm());

        stage.setTitle("Drugi Ekran!");
        stage.setScene(new Scene(root1, 580,320));
        stage.show();
    }

}
