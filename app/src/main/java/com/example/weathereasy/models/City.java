package com.example.weathereasy.models;

public class City {
    private String name;
    private String countryCode;
    private double log;
    private double lat;
    private Weather weather;


    public City() {
    }

    public City(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
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

    @Override
    public String toString() {
        return  name + ", " + countryCode  + '\n';
    }

}
