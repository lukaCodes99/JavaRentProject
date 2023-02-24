package com.example.projektrent;

import com.example.projektrent.entiteti.*;
import com.example.projektrent.iznimke.IstekRegistracijeException;
import com.example.projektrent.iznimke.KrajPrijePocetkaException;
import com.example.projektrent.iznimke.PredugaRezervacijaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RezervacijaEkranController implements Rezerviraj {
    List<Hatchback> listaVozila;


    @FXML
    private TableView<Vozilo> voziloTableView;
    @FXML
    private TableColumn<Vozilo, String> idColumn;
    @FXML
    private TableColumn<Vozilo, String> markaColumn;
    @FXML
    private TableColumn<Vozilo, String> modelColumn;
    @FXML
    private ComboBox<String> comboVrsta;

    @FXML
    private DatePicker pocetakRezervacijeDatePicker;
    @FXML
    private DatePicker krajRezervacijeDatePicker;
    List<Vozilo> vozila;
    @FXML
    private Button rezervirajButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button cijenaButton;

    public void initialize(){
        vozila = BazaPodataka.getAllVozila();
        comboVrsta.getItems().addAll("hatchback", "limuzina", "kombi");

        markaColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarka()));

        //markaColumn.setCellValueFactory(new PropertyValueFactory<>("marka"));
        modelColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        idColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID().toString()));

        voziloTableView.setItems(FXCollections.observableList(vozila));

        clearButton.setOnAction(event -> {
            comboVrsta.setValue(null);
            pocetakRezervacijeDatePicker.setValue(null);
            krajRezervacijeDatePicker.setValue(null);
            voziloTableView.setItems(FXCollections.observableList(vozila));
        });
        rezervirajButton.setOnAction(event -> {
            Vozilo selectedVehicle = voziloTableView.getSelectionModel().getSelectedItem();
            if (selectedVehicle != null) {
                try {
                    rezervirajOdabranoVozilo(selectedVehicle.getID(), pocetakRezervacijeDatePicker.getValue(), krajRezervacijeDatePicker.getValue());
                } catch (KrajPrijePocetkaException e) {
                    System.out.println("Pocetak prije kraja.");
                } catch (PredugaRezervacijaException e) {
                    System.out.println("Rezervacija predugacka.");
                } catch (IstekRegistracijeException e) {
                    System.out.println("registracija vozila je istekla.");
                } catch (Exception e) {
                    System.out.println("An unknown error occurred.");
                }
                }
            }
        );
        cijenaButton.setOnAction(event -> {
                    Vozilo selectedVehicle = voziloTableView.getSelectionModel().getSelectedItem();
                    if (selectedVehicle != null) {
                        String tipVozila= BazaPodataka.imeTabliceID(selectedVehicle.getID());

                        if(tipVozila.equals("hatchback")){
                            Hatchback hatchback = new Hatchback(selectedVehicle.getID(), null, null,
                                    null, null, null); //null jer mi drugo ne treba
                            CijenaNajma<Hatchback, LocalDate> najam = new CijenaNajma<>(hatchback,
                                    pocetakRezervacijeDatePicker.getValue(), krajRezervacijeDatePicker.getValue(),
                                    Hatchback.getCijena().doubleValue());
                            Double cijena = najam.izracunajCijenu();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Prikaz cijene!");
                            long brojDana = ChronoUnit.DAYS.between(pocetakRezervacijeDatePicker.getValue(),
                                    krajRezervacijeDatePicker.getValue());
                            if (brojDana > 10) {
                                alert.setTitle("Prikaz cijene uz 20% popusta!");
                            }
                            alert.setHeaderText("Izračun cijene za: " +selectedVehicle.getMarka() +  " " +
                                    selectedVehicle.getModel());
                            alert.setContentText("Cijena najma je " + cijena+ " €");

                            alert.showAndWait();
                        }
                        if(tipVozila.equals("limuzina")){
                            Limuzina limuzina = new Limuzina(selectedVehicle.getID(), null, null,
                                    null, null, null);
                            CijenaNajma<Limuzina, LocalDate> najam = new CijenaNajma<>(limuzina,
                                    pocetakRezervacijeDatePicker.getValue(), krajRezervacijeDatePicker.getValue(),
                                    Hatchback.getCijena().doubleValue());
                            Double cijena = najam.izracunajCijenu();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Prikaz cijene!");
                            long brojDana = ChronoUnit.DAYS.between(pocetakRezervacijeDatePicker.getValue(),
                                    krajRezervacijeDatePicker.getValue());
                            if (brojDana > 10) {
                                alert.setTitle("Prikaz cijene uz 20% popusta!");
                            }

                            alert.setHeaderText("Izračun cijene za: " +selectedVehicle.getMarka() +  " " +
                                    selectedVehicle.getModel());
                            alert.setContentText("Cijena najma je " + cijena + " €");

                            alert.showAndWait();
                        }
                        if(tipVozila.equals("kombi")){
                            Kombi kombi = new Kombi(selectedVehicle.getID(), null, null,
                                    null, null, null, null);
                            CijenaNajma<Kombi, LocalDate> najam = new CijenaNajma<>(kombi,
                                    pocetakRezervacijeDatePicker.getValue(), krajRezervacijeDatePicker.getValue(),
                                    Hatchback.getCijena().doubleValue());
                            Double cijena = najam.izracunajCijenu();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Prikaz cijene!");
                            long brojDana = ChronoUnit.DAYS.between(pocetakRezervacijeDatePicker.getValue(),
                                    krajRezervacijeDatePicker.getValue());
                            if (brojDana > 10) {
                                alert.setTitle("Prikaz cijene uz 20% popusta!");
                            }

                            alert.setHeaderText("Izračun cijene za: " +selectedVehicle.getMarka() +  " " +
                                    selectedVehicle.getModel());
                            alert.setContentText("Cijena najma je " + cijena + " €");

                            alert.showAndWait();
                        }
                    }
                }
        );
    }



    public void rezervirajOdabranoVozilo(Integer id, LocalDate pocetak, LocalDate kraj) throws
            KrajPrijePocetkaException, PredugaRezervacijaException, IstekRegistracijeException {
        rezervirajVozilo(id, pocetak, kraj);
    }

    public void dohvatiSlobodnaVozila(){
        String vrstaVozila = comboVrsta.getValue().toString();
        List<? extends Vozilo> listaVozila = new ArrayList<>();


        if(vrstaVozila.equals("hatchback") && (pocetakRezervacijeDatePicker != null) && (krajRezervacijeDatePicker!=null)){

            listaVozila=dohvatiSlobodnaHatchbackVozila(pocetakRezervacijeDatePicker.getValue(), krajRezervacijeDatePicker.getValue());
        }
        if(vrstaVozila.equals("limuzina")&&pocetakRezervacijeDatePicker != null && krajRezervacijeDatePicker!=null){
            listaVozila=dohvatiSlobodnaLimuzinaVozila(pocetakRezervacijeDatePicker.getValue(), krajRezervacijeDatePicker.getValue());
        }
        if(vrstaVozila.equals("kombi")&&pocetakRezervacijeDatePicker != null && krajRezervacijeDatePicker!=null){
            listaVozila=dohvatiSlobodnaKombiVozila(pocetakRezervacijeDatePicker.getValue(), krajRezervacijeDatePicker.getValue());
        }

        ObservableList<Vozilo> observableVozila = FXCollections.observableArrayList(listaVozila);

        voziloTableView.setItems(observableVozila);



    }
    //TODO NAPRAVITI METODU ZA DOHVAĆANJE SAMO PO ID-ju
    public List<Vozilo<Hatchback>> dohvatiSlobodnaHatchbackVozila(LocalDate pocetakRezervacije, LocalDate krajRezervacije) {
        List<Rezervacija> listaRezervacija = BazaPodataka.dohvatiSveRezervacije();
        List<Vozilo<Hatchback>> listaDostupnihVozila = new ArrayList<>();

        List<Hatchback> listaVozila=BazaPodataka.dohvatiSvaHatchbackVozila();

        for (Hatchback vozilo : listaVozila) {
            boolean dostupno = false;

            for (Rezervacija r : listaRezervacija) {
                if (r.getIdVozila().equals(vozilo.getID())) {
                    LocalDate pocetak = r.getPocetak_rezervacije();
                    LocalDate kraj = r.getKraj_rezervacije();
                    if (pocetakRezervacije.isAfter(pocetak) && pocetakRezervacije.isBefore(kraj)) {
                        dostupno=  false;
                    }
                    if (krajRezervacije.isAfter(pocetak) && krajRezervacije.isBefore(kraj)) {
                        dostupno=  false;
                    }
                    if (pocetakRezervacije.isBefore(pocetak) && krajRezervacije.isAfter(kraj)) {
                        dostupno=  false;
                    }
                    else{
                        dostupno=  true;
                    }
                }

            }
            //nisam siguran zasto, nemam vremena provjeriti ali kad maknem ! prikaze se samo rezervirani
            if (!dostupno) {
                listaDostupnihVozila.add(vozilo);
            }
        }

        return listaDostupnihVozila;
    }

    //TODO nekako povezati da se preko tipa uzimaju vozila iz tablice vozila jer ovdje dodajem odredene tipove u VOzilo sto ne radi

    public List<Vozilo> dohvatiSlobodnaLimuzinaVozila(LocalDate pocetakRezervacije, LocalDate krajRezervacije) {
        List<Rezervacija> listaRezervacija = BazaPodataka.dohvatiSveRezervacije();
        List<Vozilo> listaDostupnihVozila = new ArrayList<>();


        List<Limuzina> listaVozila=BazaPodataka.dohvatiSvaLimuzinaVozila();


        for (Limuzina vozilo : listaVozila) {
            boolean dostupno = true;

            for (Rezervacija r : listaRezervacija) {
                if (r.getIdVozila().equals(vozilo.getID())) {
                    LocalDate pocetak = r.getPocetak_rezervacije();
                    LocalDate kraj = r.getKraj_rezervacije();
                    if (pocetakRezervacije.isAfter(pocetak) && pocetakRezervacije.isBefore(kraj)) {
                        dostupno=  false;
                    }
                    if (krajRezervacije.isAfter(pocetak) && krajRezervacije.isBefore(kraj)) {
                        dostupno=  false;
                    }
                    if (pocetakRezervacije.isBefore(pocetak) && krajRezervacije.isAfter(kraj)) {
                        dostupno=  false;
                    }
                }
                dostupno=  true;
            }

            if (dostupno) {
                listaDostupnihVozila.add(vozilo);
            }
        }

        return listaDostupnihVozila;
    }

    public List<Vozilo> dohvatiSlobodnaKombiVozila(LocalDate pocetakRezervacije, LocalDate krajRezervacije) {
        List<Rezervacija> listaRezervacija = BazaPodataka.dohvatiSveRezervacije();
        List<Vozilo> listaDostupnihVozila = new ArrayList<>();


        List<Kombi> listaVozila=BazaPodataka.dohvatiSvaKombiVozila();


        for (Kombi vozilo : listaVozila) {
            boolean dostupno = true;

            for (Rezervacija r : listaRezervacija) {
                if (r.getIdVozila().equals(vozilo.getID())) {
                    LocalDate pocetak = r.getPocetak_rezervacije();
                    LocalDate kraj = r.getKraj_rezervacije();
                    if (pocetakRezervacije.isAfter(pocetak) && pocetakRezervacije.isBefore(kraj)) {
                        dostupno=  false;
                    }
                    if (krajRezervacije.isAfter(pocetak) && krajRezervacije.isBefore(kraj)) {
                        dostupno=  false;
                    }
                    if (pocetakRezervacije.isBefore(pocetak) && krajRezervacije.isAfter(kraj)) {
                        dostupno=  false;
                    }
                }
                dostupno=  true;
            }

            if (dostupno) {
                listaDostupnihVozila.add(vozilo);
            }
        }

        return listaDostupnihVozila;
    }


}
