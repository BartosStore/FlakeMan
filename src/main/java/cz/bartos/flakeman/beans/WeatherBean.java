/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.bartos.flakeman.beans;

import cz.bartos.flakeman.REST.JsonService;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author miba
 */
@Named
@ViewScoped
public class WeatherBean implements Serializable {

    private int airTemp = (int) 15.0;
    private int roomTemp = (int) 22.0;
    private String forecast;
    private String city = "valdice";

    @Inject
    private JsonService jsonService;

    public void checkWeather() {
        forecast = jsonService.getWeatherResponse(city);
    }

    public int getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(int airTemp) {
        this.airTemp = airTemp;
    }

    public int getRoomTemp() {
        return roomTemp;
    }

    public void setRoomTemp(int roomTemp) {
        this.roomTemp = roomTemp;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
