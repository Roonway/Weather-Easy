package com.example.weathereasy.models;

public class City {
    private String name;
    private Main main;
    private Sys sys;


    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @Override
    public String toString() {
        return  name + ", " + sys.getCountry();
    }

}
