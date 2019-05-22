package ba.unsa.etf.rs.tutorijal8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.BitSet;

public class TransportDAO {
    private static TransportDAO instance = null;
    private static Connection conn;

    private PreparedStatement addDriver, zadnjiVozacId;
    private TransportDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:proba.db");
            initializeStatements();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            setupDatabase();
            initializeStatements();
        }
    }

    public static TransportDAO getInstance() {
        if(instance==null) instance=new TransportDAO();
        return instance;
    }

    private void setupDatabase() {
        String sql="";
        URL x = getClass().getResource("/dbScript.sql");
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(x.getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeStatements() {
    }


    public void resetDatabase() {
    }

    public ArrayList<Driver> getDrivers() {
    }

    public void addBus(Bus bus) {
    }

    public BitSet getBusses() {
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
