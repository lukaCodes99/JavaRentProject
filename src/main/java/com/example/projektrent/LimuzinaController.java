package com.example.projektrent;

import com.example.projektrent.entiteti.Hatchback;
import com.example.projektrent.entiteti.Limuzina;
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

public class LimuzinaController {

    @FXML
    private TextField markaAutaTextField;

    @FXML
    private TextField modelAutaTextField;
    @FXML
    private ComboBox<VrstaGoriva> vrstaGorivaComboBox;
    @FXML
    private ComboBox<Integer> comboSnaga;
    @FXML
    private TableView<Limuzina> limuzinaTableView;
    @FXML
    private TableColumn<Limuzina, String> markaColumn;
    @FXML
    private TableColumn<Limuzina, String> modelColumn;
    @FXML
    private TableColumn<Limuzina, String> vrstaGorivaColumn;
    @FXML
    private TableColumn<Limuzina, String> snagaColumn;
    @FXML
    private TableColumn<Limuzina, String> IDColumn;
    @FXML
    private Button clearButton;
    @FXML
    private Button filterButton;
    List<Limuzina> limuzinaVozila;

    public void initialize() {

        limuzinaVozila = BazaPodataka.dohvatiSvaLimuzinaVozila();

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

        limuzinaTableView.setItems(FXCollections.observableList(limuzinaVozila));

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

        List<Limuzina> filteredLimuzina= limuzinaVozila.stream()
                .filter(l -> (markaAuta.isEmpty() || l.getMarka().contains(markaAuta)))
                .filter(l -> (modelAuta.isEmpty() || l.getModel().contains(modelAuta)))
                .filter(l -> (vrstaGoriva == null || l.getVrsta_goriva().equals(vrstaGoriva)))
                .filter(l -> (snaga == null || l.getSnaga() > snaga))
                .collect(Collectors.toList());

        limuzinaTableView.setItems(FXCollections.observableList(filteredLimuzina));
    }

        /*Optional<List<Hatchback>> filtriraniStudentiIme = BazaPodataka.dohvatiStudentaIme(ime);
            if(filtriraniStudentiIme.isPresent()){
                studentTableView.setItems(FXCollections.observableList(filtriraniStudentiIme.get()));
            }*/

   /*
    private List<Limuzina>  ucitajListu(){

        List<Limuzina> limuzinaVozila=new ArrayList<>();

        limuzinaVozila.add(new Limuzina(1, "Honda", "Civic", 90, LocalDate.of(2022, 01, 01), VrstaGoriva.BENZIN));
        limuzinaVozila.add(new Limuzina(2, "Toyota", "Corolla", 110, LocalDate.of(2021, 12, 01), VrstaGoriva.HIBRID));
        limuzinaVozila.add(new Limuzina(3, "Ford", "Focus", 130, LocalDate.of(2022, 05, 01), VrstaGoriva.BENZIN));
        limuzinaVozila.add(new Limuzina(6, "Ford", "Fiesta", 130, LocalDate.of(2022, 05, 01), VrstaGoriva.BENZIN));
        limuzinaVozila.add(new Limuzina(4, "Volkswagen", "Golf", 90, LocalDate.of(2022, 02, 01), VrstaGoriva.DIZEL));
        limuzinaVozila.add(new Limuzina(5, "Hyundai", "i30", 110, LocalDate.of(2021, 11, 01), VrstaGoriva.BENZIN));
        return limuzinaVozila;
    }

    */

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
