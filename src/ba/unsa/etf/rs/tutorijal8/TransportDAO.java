package ba.unsa.etf.rs.tutorijal8;

import org.sqlite.JDBC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TransportDAO {
    private static TransportDAO instance = null;
    private static Connection conn;

    private PreparedStatement dajVozace, dajBus, iskopajIdVozaca, iskopajIdBusa,
    dodajVozaca, dodajBus, obrisiVozaca, obrisiBus, dajDodjeluVB, truncVozaca, truncBusa, truncVB, dodajVB;
    private Driver driver;
    static {
        try {
            DriverManager.registerDriver(new JDBC());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TransportDAO getInstance() {
        if(instance == null) instance = new TransportDAO();
        return instance;
    }

    private TransportDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:proba.db");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        try {
            dajVozace = conn.prepareStatement("SELECT * FROM Vozac");
            dajBus = conn.prepareStatement("SELECT * FROM Bus");
            iskopajIdVozaca = conn.prepareStatement("SELECT MAX(bus_id)+1 FROM Bus");
            iskopajIdBusa = conn.prepareStatement("SELECT MAX(vozac_id)+1 FROM Vozac");
            dodajVozaca = conn.prepareStatement("INSERT INTO Vozac VALUES (?,?,?,?,?,?)");
            dodajBus = conn.prepareStatement("INSERT INTO Bus VALUES (?,?,?,?,?)");
            obrisiVozaca = conn.prepareStatement("DELETE FROM Vozac WHERE vozac_id = ?");
            truncVozaca = conn.prepareStatement("DELETE FROM Vozac");
            obrisiBus = conn.prepareStatement("DELETE FROM Bus WHERE bus_id = ?");
            truncBusa = conn.prepareStatement("DELETE FROM Bus");
            truncVB = conn.prepareStatement("DELETE FROM dodjelaBus");
            dajDodjeluVB = conn.prepareStatement("SELECT DISTINCT v.vozac_id, v.ime," +
                    "v.prezime, v.jmb, v.datum_rodjenja, v.datum_zaposlenja" + " FROM dodjelaBus db, Vozac v WHERE dB.driverId = v.vozac_id AND dB.busId = ?");
            dodajVB = conn.prepareStatement("INSERT INTO dodjelaBus VALUES (?,?,null)");

        } catch (SQLException e) {
            regen();
            e.printStackTrace();
        }
    }

    public void regen() {
        Scanner s = null;
        try {
            s = new Scanner(new FileInputStream("/probaSQL.sql"));
            String SQLQuery = "";
            while (s.hasNext()) {
                SQLQuery += s.nextLine();
                if (SQLQuery.charAt(SQLQuery.length() - 1) == ';'){
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(SQLQuery);
                        SQLQuery = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public ArrayList<Driver> getDrivers() {
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        ResultSet result = null;
        try {
            result = dajVozace.executeQuery();
            Driver driver;
            while (  ( driver = dajVozace(result) ) != null )
                drivers.add(driver);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    private Driver dajVozace(ResultSet result) {
        Driver driver = null;
        try {
            if(result.next()){
                int idDriver = result.getInt("vozac_id");
                String name = result.getString("ime");
                String surname = result.getString("prezime");
                String jmb = result.getString("jmb");
                LocalDate birthday = convertToLocalDateViaSqlDate(result.getDate("datum_rodjenja"));
                LocalDate hireDate = convertToLocalDateViaSqlDate(result.getDate("datum_zaposlenja"));

                driver = new Driver (name, surname, jmb, birthday, hireDate);
                driver.setIdDriver(idDriver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public void addBus(Bus bus) {
        try {
            ResultSet rset = iskopajIdBusa.executeQuery();
            int id = 1;
            if(rset.next()) {
                id = rset.getInt(1);
            }
            dodajBus.setInt(1, id);
            dodajBus.setString(2, bus.getMaker());
            dodajBus.setString(3, bus.getSeries());
            dodajBus.setInt(4, bus.getSeatNumber());
            dodajBus.setInt(5, bus.getNoOfDrivers());
            dodajBus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bus> getBusses() {
        ArrayList<Bus> buses = new ArrayList<>();
        try {
            ResultSet result = dajBus.executeQuery();
            while (result.next()){
                Integer idBus = result.getInt(1);
                String maker = result.getString(2);
                String series = result.getString(3);
                int seatNumber = result.getInt(4);
                dajDodjeluVB.setInt(1, idBus);

                ResultSet resultCvaj = dajDodjeluVB.executeQuery();
                Driver driver;
                ArrayList<Driver> drivers = new ArrayList<Driver>();
                while (resultCvaj.next()) {
                    Integer idDriver = resultCvaj.getInt(1);
                    String name = resultCvaj.getString(2);
                    String surname = resultCvaj.getString(3);
                    String jmb = resultCvaj.getString(4);
                    Date birthday = resultCvaj.getDate(5);
                    Date hireDate = resultCvaj.getDate(5);
                    drivers.add(new Driver(idDriver, name, surname, jmb, birthday.toLocalDate(), hireDate.toLocalDate()));

                }
                if (drivers.size() == 1){
                    buses.add(new Bus(idBus, maker, series, seatNumber, drivers.get(0), null));
                }
                else if (drivers.size() == 2){
                    buses.add(new Bus(idBus, maker, series, seatNumber, drivers.get(0), drivers.get(1)));
                }
                else {
                    buses.add(new Bus(idBus, maker, series, seatNumber, null, null));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    public void deleteBus(Bus bus) {
        try {
            obrisiBus.setInt(1, bus.getIdBus());
            obrisiBus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDriver(Driver driver) {
        try {
            ResultSet rs = iskopajIdVozaca.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            dodajVozaca.setInt(1, id);
            dodajVozaca.setString(2, driver.getName());
            dodajVozaca.setString(3, driver.getSurname());
            dodajVozaca.setString(4 , driver.getJmb());
            dodajVozaca.setDate(5 , convertToDateViaSqlDate(driver.getBirthday()));
            dodajVozaca.setDate(6 , convertToDateViaSqlDate(driver.getHireDate()));
            dodajVozaca.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Taj vozač već postoji!");
        }
    }

    public void deleteDriver(Driver driver) {
        try {
            obrisiVozaca.setInt(1, driver.getIdDriver());
            obrisiVozaca.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodijeliVozacuAutobus(Driver driver, Bus bus, int koji) {
        try {
            dodajVB.setInt(1, bus.getIdBus());
            dodajVB.setInt(2, driver.getIdDriver());
            dodajVB.executeUpdate();
            if(koji == 1){
                bus.setDriverOne(driver);
            }
            if(koji == 2){
                bus.setDriverTwo(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetDatabase() {
        try {
            truncVB.executeUpdate();
            truncBusa.executeUpdate();
            truncVozaca.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
