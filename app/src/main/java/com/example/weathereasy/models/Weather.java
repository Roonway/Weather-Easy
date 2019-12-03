package com.example.weathereasy.models;

public class Weather {
    private double temp;
    private double maxTemp;
    private double minTemp;


    public Weather() {
    }

    public Weather(double temp, double maxTemp, double minTemp) {
        this.temp = temp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    @Override
    public String toString() {
        return "Temperatura: " + temp + " °C"
                + "\nMáxima: " + maxTemp + " °C"
                + "\nMiníma: " + minTemp + " °C";
    }
}
