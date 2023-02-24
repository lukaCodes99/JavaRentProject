package com.example.projektrent;

import com.example.projektrent.entiteti.Limuzina;
import com.example.projektrent.entiteti.VrstaGoriva;
import com.example.projektrent.iznimke.IDmoraBitiBrojException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class NewLimuzinaController {

    @FXML
    private TextField markaField;
    @FXML
    private TextField idField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField snagaField;

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

        //treba dodati sve uvjete!!
        if(id.toString().isBlank() || marka.isBlank() || model.isBlank()
                || gorivo.isBlank() || registracija==null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Spremanje Limuzina");
            alert.setHeaderText("Niste uspjeli unijeti novog Limuzina!");
            alert.setContentText("Potrebno je ispuniti sva polja za unos kako bi se pravilno spremio novi Limuzina");

            alert.showAndWait();
        }

       else {
            Limuzina limuzina = new Limuzina(id, marka, model, snaga, registracija, VrstaGoriva.valueOf(gorivo));
            BazaPodataka.addNewLimuzina(limuzina);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Spremanje Limuzina");
            alert.setHeaderText("Uspješno dodan nova Limuzina!");
            alert.setContentText("Limuzina " + limuzina.getMarka() +" " + limuzina.getModel() +" je uspjesno dodana u aplikaciju");
            alert.showAndWait();
        }
    }
}
