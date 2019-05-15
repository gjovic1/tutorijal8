package ba.unsa.etf.rs.tutorijal8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransportDAO {
    private static TransportDAO instance = null;
    private static Connection conn; 

    private static PreparedStatement addDriver;
    private static PreparedStatement addBus;
    private static PreparedStatement addBusToDriver;
    private static PreparedStatement deleteBus;
    private static PreparedStatement deleteDriver;
    
    private TransportDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
            initializeStatements();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            setupDatabase();
            initializeStatements();
        }
    }

    private void setupDatabase() {
        String sql="";
        URL x = getClass().getResource("/setupDatabase.sql");
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(x.getFile());

        } catch (SQLException e) {

        }
    }

    private void initializeStatements() {
    }


}
