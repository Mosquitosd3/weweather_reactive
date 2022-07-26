package ru.job4j.weather.weather_reactive.model;

public class Weather {
    private int id;

    private String city;

    private int temperature;

    public Weather(String city, int temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public Weather(int id, String city, int temperature) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
