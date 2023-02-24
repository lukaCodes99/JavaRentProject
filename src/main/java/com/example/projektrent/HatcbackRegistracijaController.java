package com.example.projektrent;

import com.example.projektrent.entiteti.Hatchback;
import com.example.projektrent.entiteti.Vozilo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HatcbackRegistracijaController {
    @FXML
    private TableView<Hatchback> hatchbackTableView;
    @FXML
    private TableColumn<Hatchback, String> markaColumn;
    @FXML
    private TableColumn<Hatchback, String> modelColumn;

    @FXML
    private TableColumn<Hatchback, String> istekTableColumn;

    @FXML
    private TableColumn<Hatchback, String> IDColumn;
    List<Hatchback> hatchbackVozila;
    @FXML
    private Button azurirajButton;
    @FXML
    private DatePicker registracijaPicker;

    public void initialize(){
        hatchbackVozila=BazaPodataka.dohvatiSvaHatchbackVozila();

        hatchbackVozila=istekRegistracije();

        markaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarka()));

        modelColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        istekTableColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Hatchback, String>,
                        ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(
                            TableColumn.CellDataFeatures<Hatchback, String> hatchback) {
                        SimpleStringProperty property = new
                                SimpleStringProperty();
                        DateTimeFormatter formatter =
                                DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        property.setValue(
                                hatchback.getValue().getIstekRegistracije().format(formatter));
                        return property;
                    }
                });
        IDColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID().toString()));

        hatchbackTableView.setItems(FXCollections.observableList(hatchbackVozila));

        azurirajButton.setOnAction(event -> {
            LocalDate registracija = registracijaPicker.getValue();
            Hatchback hatchback= hatchbackTableView.getSelectionModel().getSelectedItem();
            BazaPodataka.updateHatchbackIstekRegistracije(hatchback.getID(), registracija);
        }
        );
    }

    private List<Hatchback> istekRegistracije() {
        List<Hatchback> isticeRega=new ArrayList<>();
        LocalDate now = LocalDate.now();
        for (Hatchback h : hatchbackVozila) {
            long daysUntilIstek = ChronoUnit.DAYS.between(now, h.getIstekRegistracije());
            if (daysUntilIstek >= 0 && daysUntilIstek <= 7) {
                isticeRega.add(h);
            }
        }
        return isticeRega;
    }
}
