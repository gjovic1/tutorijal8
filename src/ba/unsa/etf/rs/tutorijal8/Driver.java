package ba.unsa.etf.rs.tutorijal8;

import java.time.LocalDate;

public class Driver {
    private Integer idDriver = null;
    private String name, surname, jmb;
    private LocalDate birthday, hireDate;

    public Driver(){

    }

    public Driver (String name, String surname, String jmb, LocalDate birthday, LocalDate hireDate){
        this.name=name;
        this.surname=surname;
        this.jmb=jmb;
        this.birthday=birthday;
        this.hireDate=hireDate;
    }

    public Driver (Integer idDriver, String name, String surname, String jmb, LocalDate birthday, LocalDate hireDate){
        this.idDriver=idDriver;
        this.name=name;
        this.surname=surname;
        this.jmb=jmb;
        this.birthday=birthday;
        this.hireDate=hireDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getJmb() {
        return jmb;
    }

    public void setJmb(String jmb) {
        this.jmb = jmb;
    }

    public Integer getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Integer idDriver) {
        this.idDriver = idDriver;
    }

    @Override
    public String toString() {
        return " -> (" + this.getName() + " " + this.getSurname() + " ( " + this.getJmb() + " ))";
    }

    public boolean equals (Driver driver){
        return (driver.getJmb().equals(this.getJmb()));
    }
}
