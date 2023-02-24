package com.example.projektrent;

import com.example.projektrent.entiteti.*;
import javafx.scene.control.Alert;

import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BazaPodataka {

    private static Connection connectToDatabase() throws Exception{

        Properties konfiguracijaBaze=new Properties();

        konfiguracijaBaze.load(new FileInputStream("dat/bazaPodataka.properties"));

        Connection con= DriverManager.getConnection(
                konfiguracijaBaze.getProperty("bazaPodatakaURL"),
                konfiguracijaBaze.getProperty("korisnickoIme"),
                konfiguracijaBaze.getProperty("lozinka"));

        if(con!=null){
            System.out.println("Uspjesno smo se spojili na bazu");
        }
        return con;

    }

    public static String  imeTabliceID(int id) {
        try (Connection conn = BazaPodataka.connectToDatabase()) {
            String sql = "SELECT 'HATCHBACK' AS table_name FROM HATCHBACK WHERE id = ? " +
                    "UNION " +
                    "SELECT 'LIMUZINA' AS table_name FROM LIMUZINA WHERE id = ? " +
                    "UNION " +
                    "SELECT 'KOMBI' AS table_name FROM KOMBI WHERE id = ? ";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setInt(2, id);
                stmt.setInt(3, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("table_name").toLowerCase();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }





    //TODO NAPRAVITI FUNKCIJU KOJA CE SE POZIVATI U INTERFACE bAZA PODATAKA.NAPRAVIREZERVACIJU(ID, POCETAK, KRAJ)

    public static List<Rezervacija> dohvatiSveRezervacije() {
        List<Rezervacija> listaRezervacija = new ArrayList<>();

        try {
            Connection con = connectToDatabase();
            if(con!=null){
                System.out.println("Uspjesno smo se spojili na bazu");
            }
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM REZERVACIJE");

             while (rs.next()) {
                 int id = rs.getInt("IDVOZILA");
                 LocalDate pocetakRezervacije = rs.getDate("pocetak_rezervacije").toLocalDate();
                 LocalDate krajRezervacije = rs.getDate("kraj_rezervacije").toLocalDate();

                 Rezervacija novaRezervacija = new Rezervacija(pocetakRezervacije, krajRezervacije, id);
                 listaRezervacija.add(novaRezervacija);
        }

        rs.close();
        stmt.close();
        con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listaRezervacija;
    }

    public static void dodajRezervaciju(Rezervacija novaRezervacija) {
        try {
            Connection con = connectToDatabase();
            if(con!=null){
                System.out.println("Uspjesno smo se spojili na bazu");
            }

            String query = "INSERT INTO REZERVACIJE (pocetak_rezervacije, kraj_rezervacije, idvozila) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setDate(1, java.sql.Date.valueOf(novaRezervacija.getPocetak_rezervacije()));
            statement.setDate(2, java.sql.Date.valueOf(novaRezervacija.getKraj_rezervacije()));
            statement.setInt(3, novaRezervacija.getIdVozila());
            statement.executeUpdate();

            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   public static List<Vozilo> getAllVozila() {
        List<Vozilo> vozila = new ArrayList<Vozilo>();
        try {
            Connection con = connectToDatabase();
            if(con!=null){
                System.out.println("Uspjesno smo se spojili na bazu");
            }

            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT v.ID, v.TIPVOZILA,\n" +
                    "    COALESCE(l.MARKA, h.MARKA, k.MARKA) AS MARKA,\n" +
                    "    COALESCE(l.MODEL, h.MODEL, k.MODEL) AS MODEL\n" +
                    "FROM VOZILA v\n" +
                    "LEFT JOIN LIMUZINA l ON v.ID = l.ID AND v.TIPVOZILA = 'Limuzina'\n" +
                    "LEFT JOIN HATCHBACK h ON v.ID = h.ID AND v.TIPVOZILA = 'Hatchback'\n" +
                    "LEFT JOIN KOMBI k ON v.ID = k.ID AND v.TIPVOZILA = 'Kombi';\n");

            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String tipVozila = rs.getString("tipVozila");
                String marka = rs.getString("marka");
                String model = rs.getString("model");


                Vozilo vozilo;
                switch (tipVozila) {
                    case "Kombi":
                        vozilo = new Kombi(id, marka, model, null, null, null, null);
                        break;
                    case "Hatchback":
                        vozilo = new Hatchback(id, marka, model, null, null, null);
                        break;
                    case "Limuzina":
                        vozilo = new Limuzina(id, marka, model, null, null, null);
                        break;
                    default:
                        throw new IllegalArgumentException("Nepostojeci tip vozila: " + tipVozila);
                }

                vozila.add(vozilo);
            }

            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return vozila;
    }


    public static List<Hatchback> dohvatiSvaHatchbackVozila(){
        List<Hatchback> listaVozila=new ArrayList<>();
        try {
            Connection con = connectToDatabase();
            if(con!=null){
                System.out.println("Uspjesno smo se spojili na bazu");
            }
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * FROM HATCHBACK");

            while(rs.next()){
                Integer id=rs.getInt("id");
                String marka=rs.getString("marka");
                String model = rs.getString("model");
                Integer snaga=rs.getInt("snaga");
                LocalDate istekRegistracije=rs.getDate("istekRegistracije").toLocalDate();

                String vrstaGorivaString = rs.getString("vrstaGoriva");
                VrstaGoriva vrstaGoriva = VrstaGoriva.valueOf(vrstaGorivaString);
                Hatchback noviHatchback = new Hatchback(id, marka, model, snaga, istekRegistracije, vrstaGoriva);

                listaVozila.add(noviHatchback);
            }
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listaVozila;
    }

    public static List<Limuzina> dohvatiSvaLimuzinaVozila(){
        List<Limuzina> listaVozila=new ArrayList<>();
        try {
            Connection con = connectToDatabase();
            if(con!=null){
                System.out.println("Uspjesno smo se spojili na bazu");
            }
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * FROM LIMUZINA");

            while(rs.next()){
                Integer id=rs.getInt("id");
                String marka=rs.getString("marka");
                String model = rs.getString("model");
                Integer snaga=rs.getInt("snaga");
                LocalDate istekRegistracije=rs.getDate("istekRegistracije").toLocalDate();

                String vrstaGorivaString = rs.getString("vrstaGoriva");
                VrstaGoriva vrstaGoriva = VrstaGoriva.valueOf(vrstaGorivaString);
                Limuzina novaLimuzina = new Limuzina(id, marka, model, snaga, istekRegistracije, vrstaGoriva);

                listaVozila.add(novaLimuzina);
            }
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listaVozila;
    }
    public static List<Kombi> dohvatiSvaKombiVozila(){
        List<Kombi> listaVozila=new ArrayList<>();
        try {
            Connection con = connectToDatabase();
            if(con!=null){
                System.out.println("Uspjesno smo se spojili na bazu");
            }
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("SELECT * FROM KOMBI");

            while(rs.next()){
                Integer id=rs.getInt("id");
                String marka=rs.getString("marka");
                String model = rs.getString("model");
                Integer snaga=rs.getInt("snaga");
                LocalDate istekRegistracije=rs.getDate("istekRegistracije").toLocalDate();
                String vrstaGorivaString = rs.getString("vrstaGoriva");
                VrstaGoriva vrstaGoriva = VrstaGoriva.valueOf(vrstaGorivaString);
                Integer brojSjedala=rs.getInt("BROJSJEDALA");
                Kombi novKombi = new Kombi(id, marka, model, snaga, istekRegistracije, vrstaGoriva, brojSjedala);

                listaVozila.add(novKombi);
            }
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listaVozila;
    }

    public static void addNewHatchback(Hatchback hatchback) {
        try (Connection connection = connectToDatabase()) {
            PreparedStatement hatchbackPreparedStatement = connection.prepareStatement("INSERT INTO " +
                    "HATCHBACK (ID, marka, model, snaga, istekregistracije, vrstagoriva, cijena) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
            hatchbackPreparedStatement.setInt(1, hatchback.getID());
            hatchbackPreparedStatement.setString(2, hatchback.getMarka());
            hatchbackPreparedStatement.setString(3, hatchback.getModel());
            hatchbackPreparedStatement.setInt(4, hatchback.getSnaga());
            hatchbackPreparedStatement.setDate(5, java.sql.Date.valueOf(hatchback.getIstekRegistracije()));
            hatchbackPreparedStatement.setString(6, hatchback.getVrsta_goriva().toString());
            hatchbackPreparedStatement.setInt(7, 40);

            hatchbackPreparedStatement.executeUpdate();

            PreparedStatement vozilaPreparedStatement = connection.prepareStatement("INSERT INTO VOZILA (ID, tipvozila) VALUES (?, ?)");
            vozilaPreparedStatement.setInt(1, hatchback.getID());
            vozilaPreparedStatement.setString(2, "Hatchback");
            vozilaPreparedStatement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void updateHatchbackIstekRegistracije(int hatchbackID, LocalDate newIstekRegistracije) {
        try (Connection connection = connectToDatabase()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE HATCHBACK SET istekregistracije = ? WHERE ID = ?");
            preparedStatement.setDate(1, java.sql.Date.valueOf(newIstekRegistracije));
            preparedStatement.setInt(2, hatchbackID);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addNewKombi(Kombi kombi) {
        try (Connection connection = connectToDatabase()) {

            PreparedStatement kombiPreparedStatement = connection.prepareStatement("INSERT INTO " +
                    "KOMBI (ID, marka, model, snaga, istekregistracije, vrstagoriva, brojsjedala, cijena) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            kombiPreparedStatement.setInt(1, kombi.getID());
            kombiPreparedStatement.setString(2, kombi.getMarka());
            kombiPreparedStatement.setString(3, kombi.getModel());
            kombiPreparedStatement.setInt(4, kombi.getSnaga());
            kombiPreparedStatement.setDate(5, java.sql.Date.valueOf(kombi.getIstekRegistracije()));
            kombiPreparedStatement.setString(6, kombi.getVrsta_goriva().toString());
            kombiPreparedStatement.setInt(7, kombi.getBrojSjedala());
            kombiPreparedStatement.setInt(8, 90);
            kombiPreparedStatement.executeUpdate();


            PreparedStatement vozilaPreparedStatement = connection.prepareStatement("INSERT INTO VOZILA (ID, tipvozila) VALUES (?, ?)");
            vozilaPreparedStatement.setInt(1, kombi.getID());
            vozilaPreparedStatement.setString(2, "Kombi");
            vozilaPreparedStatement.executeUpdate();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addNewLimuzina(Limuzina limuzina) {
        try (Connection connection = connectToDatabase()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO LIMUZINA (id, marka, model, snaga, istekregistracije, vrstagoriva, cijena) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, limuzina.getID());
            preparedStatement.setString(2, limuzina.getMarka());
            preparedStatement.setString(3, limuzina.getModel());
            preparedStatement.setInt(4, limuzina.getSnaga());
            preparedStatement.setDate(5, java.sql.Date.valueOf(limuzina.getIstekRegistracije()));
            preparedStatement.setString(6, limuzina.getVrsta_goriva().toString());
            preparedStatement.setInt(7, 70);
            preparedStatement.executeUpdate();

            // Insert into Vozila table
            PreparedStatement vozilaStatement = connection.prepareStatement("INSERT INTO Vozila (ID, TIPVOZILA) VALUES (?, ?)");
            vozilaStatement.setInt(1, limuzina.getID());
            vozilaStatement.setString(2, "Limuzina");
            vozilaStatement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
