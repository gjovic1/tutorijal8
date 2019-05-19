package ba.unsa.etf.rs.tutorijal8;

import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransportDAO {
    private static TransportDAO instance = null;
    private static Connection conn; 

    private PreparedStatement addDriver, zadnjiVozacId,
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

    public static TransportDAO getInstance() {
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


    public void resetDatabase() {
    }
}
