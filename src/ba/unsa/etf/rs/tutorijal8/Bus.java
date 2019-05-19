package ba.unsa.etf.rs.tutorijal8;

public class Bus {
    private Integer idBus = null;
    private String maker, series;
    private int seatNumber;
    private Driver driverOne = null;
    private Driver driverTwo = null;


    public Bus(String maker, String series, int seatNumber){
        this.maker = maker;
        this.series = series;
        this.seatNumber = seatNumber;
    }

    public Bus (Integer idBus, String maker, String series, int seatNumber){
        this.idBus = idBus;
        this.maker = maker;
        this.series = series;
        this.seatNumber = seatNumber;
    }

    public Bus (Integer idBus, String maker, String series, int seatNumber, Driver driverOne, Driver driverTwo){
        this.idBus = idBus;
        this.maker = maker;
        this.series = series;
        this.seatNumber = seatNumber;
        this.driverOne = driverOne;
        this.driverTwo = driverTwo;
    }

    public Bus() {

    }


    public Integer getIdBus() {
        return idBus;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Driver getDriverOne() {
        return driverOne;
    }

    public void setDriverOne(Driver driverOne) {
        this.driverOne = driverOne;
    }

    public Driver getDriverTwo() {
        return driverTwo;
    }

    public void setDriverTwo(Driver driverTwo) {
        this.driverTwo = driverTwo;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
