package ba.unsa.etf.rs.tutorijal8;

import org.sqlite.JDBC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.*;
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
        if(instance==null) instance=new TransportDAO();
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
            s = new Scanner(new FileInputStream("probaSQL.sql"));
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

    private void initializeStatements() {
    }


    public void resetDatabase() {
    }

    public ArrayList<Driver> getDrivers() {
        return getDrivers();
    }

    public void addBus(Bus bus) {
    }

    public ArrayList<Bus> getBusses() {
        return getBusses();
    }

    public void deleteBus(Bus bus) {
    }

    public void addDriver(Driver driver) {
    }

    public void deleteDriver(Driver driver) {
    }

    public void dodijeliVozacuAutobus(Driver driver, Bus bus, int which) {
    }
}
