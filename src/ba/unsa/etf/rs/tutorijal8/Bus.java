package ba.unsa.etf.rs.tutorijal8;

public class Bus {
    private String manufacturer;
    private int series;
    private int seatNumber;
    private final int[] drivers = {1,2,3};

    public Bus() {

    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int[] getDrivers() {
        return drivers;
    }
    public int[] setDrivers() {

    }
}
