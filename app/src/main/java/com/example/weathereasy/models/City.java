package com.example.weathereasy.models;

public class City {
    private int id;
    private String name;
    private String countryCode;
    private short log;
    private short lat;


    public City() {
    }

    public City(int id, String name, String countryCode, short log, short lat) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.log = log;
        this.lat = lat;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public short getLog() {
        return log;
    }

    public void setLog(short log) {
        this.log = log;
    }

    public short getLat() {
        return lat;
    }

    public void setLat(short lat) {
        this.lat = lat;
    }

}
