package com.example.projektrent;

import com.example.projektrent.HelloApplication;
import com.example.projektrent.entiteti.Hatchback;
import com.example.projektrent.entiteti.Vozilo;
import com.example.projektrent.entiteti.VrstaGoriva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class HatchbackController {

    @FXML
    private TextField markaAutaTextField;

    @FXML
    private TextField modelAutaTextField;
    @FXML
    private ComboBox<VrstaGoriva> vrstaGorivaComboBox;
    @FXML
    private ComboBox<Integer> comboSnaga;
    @FXML
    private TableView<Hatchback> hatchbackTableView;
    @FXML
    private TableColumn<Hatchback, String> markaColumn;
    @FXML
    private TableColumn<Hatchback, String> modelColumn;
    @FXML
    private TableColumn<Hatchback, String> vrstaGorivaColumn;
    @FXML
    private TableColumn<Hatchback, String> snagaColumn;
    @FXML
    private TableColumn<Hatchback, String> IDColumn;
    @FXML
    private Button clearButton;
    @FXML
    private Button filterButton;
    List<Hatchback> hatchbackVozila;

    public void initialize() {

        hatchbackVozila = BazaPodataka.dohvatiSvaHatchbackVozila();

        markaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarka()));

        //markaColumn.setCellValueFactory(new PropertyValueFactory<>("marka"));
        modelColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        vrstaGorivaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVrsta_goriva().toString()));
        snagaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSnaga().toString()));
        IDColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID().toString()));

        hatchbackTableView.setItems(FXCollections.observableList(hatchbackVozila));

        vrstaGorivaComboBox.getItems().addAll(VrstaGoriva.values());
        comboSnaga.getItems().addAll(90, 110, 130);

        /* primjer listenera, ali automatski mi filtrira a to ne zelim
        vrstaGorivaComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(hatchbackVozila);
        });
         */

        clearButton.setOnAction(event -> {
            vrstaGorivaComboBox.setValue(null);
            comboSnaga.setValue(null);
            markaAutaTextField.setText("");
            modelAutaTextField.setText("");
            filterVozila();
        });

        filterButton.setOnAction(event -> {
            filterVozila();
        });

        filterVozila();
    }

    /*private void filterTable( List<Hatchback> hatchbackVozila) {
        List<Hatchback> filteredList = hatchbackVozila.stream()
                .filter(hatchback -> hatchback.getVrsta_goriva() == vrstaGorivaComboBox.getValue())
                .collect(Collectors.toList());
        hatchbackTableView.setItems(FXCollections.observableList(filteredList));
    }
     */

    public void filterVozila() {
        String markaAuta = markaAutaTextField.getText();
        String modelAuta = modelAutaTextField.getText();
        VrstaGoriva vrstaGoriva = vrstaGorivaComboBox.getValue();
        Integer snaga = comboSnaga.getValue();


        //dodati toLowerCase() tako da se normalno filtrira bez obzira na uppercase

        List<Hatchback> filteredHatchback = hatchbackVozila.stream()
                .filter(h -> (markaAuta.isEmpty() || h.getMarka().contains(markaAuta)))
                .filter(h -> (modelAuta.isEmpty() || h.getModel().contains(modelAuta)))
                .filter(h -> (vrstaGoriva == null || h.getVrsta_goriva().equals(vrstaGoriva)))
                .filter(h -> (snaga == null || h.getSnaga() > snaga))
                .collect(Collectors.toList());

        hatchbackTableView.setItems(FXCollections.observableList(filteredHatchback));
    }



    public void showRezervacijaScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rezervacijaEkran.fxml"));
        Parent root1=(Parent) fxmlLoader.load();
        Stage stage=new Stage();

        //promijeniti stylesheet, mozda dodati prije nego sto se sve loada da se napravi neka animacija....
        //stage.getStylesheets().add(HelloApplication.class.getResource("hatchbackStyle.css").toExternalForm());

        stage.setTitle("Drugi Ekran!");
        stage.setScene(new Scene(root1, 580, 320));
        stage.show();
    }

}
