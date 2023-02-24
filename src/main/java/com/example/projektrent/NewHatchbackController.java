package com.example.projektrent;

import com.example.projektrent.entiteti.Hatchback;
import com.example.projektrent.entiteti.Limuzina;
import com.example.projektrent.entiteti.Serijaliziraj;
import com.example.projektrent.entiteti.VrstaGoriva;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;

public class NewHatchbackController implements Serializable {
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


    @FXML
    private Button addButton;

    public void initialize() {
        vrstaGorivaComboBox.getItems().addAll(VrstaGoriva.values());
    }

    @FXML
    private void handleAddButton() {
        Integer id=Integer.parseInt(idField.getText());
        String marka = markaField.getText();
        String model = modelField.getText();
        Integer snaga = Integer.parseInt(snagaField.getText());
        LocalDate registracija = registracijaPicker.getValue();
        String gorivo = vrstaGorivaComboBox.getValue().toString();

        if(id.toString().isBlank() || marka.isBlank() || model.isBlank()
                || gorivo.isBlank() || registracija==null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Spremanje Hatchbacka");
            alert.setHeaderText("Niste uspjeli unijeti novog Hatchbacka!");
            alert.setContentText("Potrebno je ispuniti sva polja za unos kako bi se pravilno spremio novi Hatchbacka");

            alert.showAndWait();
        }

       else {
            Hatchback hatch = new Hatchback(id, marka, model, snaga, registracija, VrstaGoriva.valueOf(gorivo));
            BazaPodataka.addNewHatchback(hatch);


            try {
                Serijaliziraj.serialize(hatch);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Spremanje Hatchback");
            alert.setHeaderText("Uspješno dodan novi Hatchback!");
            alert.setContentText("Hatchback " + marka +" " + model +" je uspjesno dodan u aplikaciju");
            alert.showAndWait();
        }
    }

    
}
