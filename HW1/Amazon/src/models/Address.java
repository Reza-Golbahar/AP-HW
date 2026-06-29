package models;

import java.util.Objects;

public class Address {
    private String country;
    private String city;
    private String street;
    private String postal;
    private int ID;


    public Address(String country, String city, String street, String postal, int ID) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postal = postal;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }
}
