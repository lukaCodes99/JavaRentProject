package com.example.projektrent;

import com.example.projektrent.entiteti.Hatchback;
import com.example.projektrent.entiteti.Kombi;
import com.example.projektrent.entiteti.VrstaGoriva;
import com.example.projektrent.iznimke.IDmoraBitiBrojException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class NewKombiController {
    @FXML
    private TextField markaField;
    @FXML
    private TextField idField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField snagaField;
    @FXML
    private TextField brojSjedalaField;
    @FXML
    private DatePicker registracijaPicker;

    @FXML
    private ComboBox<VrstaGoriva> vrstaGorivaComboBox;

    private Integer id;

    @FXML
    private Button addButton;

    public void initialize() {
        vrstaGorivaComboBox.getItems().addAll(VrstaGoriva.values());
    }

    @FXML
    private void handleAddButton() throws IDmoraBitiBrojException {


        try {
            id=Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            throw new IDmoraBitiBrojException("ID mora biti broj!", e);
        }
        String marka = markaField.getText();
        String model = modelField.getText();
        Integer snaga = Integer.parseInt(snagaField.getText());
        LocalDate registracija = registracijaPicker.getValue();
        String gorivo = vrstaGorivaComboBox.getValue().toString();
        Integer brojSjedala=Integer.parseInt(brojSjedalaField.getText());

        //treba dodati sve uvjete!!
        if(id.toString().isBlank() || marka.isBlank() || model.isBlank()
                || gorivo.isBlank() || registracija==null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Spremanje Kombi");
            alert.setHeaderText("Niste uspjeli unijeti novog Kombi!");
            alert.setContentText("Potrebno je ispuniti sva polja za unos kako bi se pravilno spremio novi Kombi");

            alert.showAndWait();
        }

       else {
            Kombi kombi = new Kombi(id, marka, model, snaga, registracija, VrstaGoriva.valueOf(gorivo), brojSjedala);
            BazaPodataka.addNewKombi(kombi);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Spremanje Kombi");
            alert.setHeaderText("Uspješno dodan novi kombi!");
            alert.setContentText("kombi " + kombi.getMarka() +" " + kombi.getModel() +" je uspjesno dodan u aplikaciju");
            alert.showAndWait();
        }
    }
}
