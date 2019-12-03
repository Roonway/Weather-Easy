package com.example.weathereasy.models;

public class Main {
    private double temp;
    private double temp_max;
    private double temp_min;
    private int pressure;
    private int humidity;

    public Main() {
    }


    public Main(double temp, double temp_max, double temp_min, int pressure, int humidity) {
        this.temp = temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }



    @Override
    public String toString() {
        return "Temperatura: " + temp + " °C"
                + "\nMáxima: " + temp_max + " °C"
                + "\t\tMiníma: " + temp_min + " °C";
    }
}
