package ba.unsa.etf.rs.tutorijal8;

import java.util.ArrayList;

public class Bus {
    private String maker;
    private int series, seatNumber;
    private ArrayList<Driver> drivers;

    public Bus(String oem, int serija, int s){
        maker = oem;
        series = serija;
        seatNumber = s;
    }
}
