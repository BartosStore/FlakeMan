/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.bartos.flakeman.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author miba
 */
@Stateless
public class WeatherServiceImpl implements WeatherService {
    
    static final String URL_OWM = "http://api.openweathermap.org/data/2.5/weather?q=Valdice,cz";

    @Override
    public String getWeatherInfo() {
        
        String result = "";
        String weatherResult = null;
        
        try {
            URL url_weather = new URL(URL_OWM);
            
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_weather.openConnection();
            
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                
                bufferedReader.close();
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(WeatherServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WeatherServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            weatherResult = ParseResult(result);
        } catch (JSONException ex) {
            Logger.getLogger(WeatherServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return StringEscapeUtils.unescapeJava(weatherResult);
    }
    
    private String ParseResult(String json) throws JSONException {
         
        String parsedResult = "";
      
        JSONObject jsonObject = new JSONObject(json);
 
        parsedResult += "Number of object = " + jsonObject.length() + "\n\n";
      
        //"coord"
        JSONObject JSONObject_coord = jsonObject.getJSONObject("coord");
        Double result_lon = JSONObject_coord.getDouble("lon");
        Double result_lat = JSONObject_coord.getDouble("lat");
      
        //"sys"
        JSONObject JSONObject_sys = jsonObject.getJSONObject("sys");
        String result_country = JSONObject_sys.getString("country");
        int result_sunrise = JSONObject_sys.getInt("sunrise");
        int result_sunset = JSONObject_sys.getInt("sunset");
         
        //"weather"
        String result_weather;
        JSONArray JSONArray_weather = jsonObject.getJSONArray("weather");
        if(JSONArray_weather.length() > 0){
            JSONObject JSONObject_weather = JSONArray_weather.getJSONObject(0);
            int result_id = JSONObject_weather.getInt("id");
            String result_main = JSONObject_weather.getString("main");
            String result_description = JSONObject_weather.getString("description");
            String result_icon = JSONObject_weather.getString("icon");
         
            result_weather = "weather\tid: " + result_id +"\tmain: " + result_main + "\tdescription: " + result_description + "\ticon: " + result_icon;
        }else{
            result_weather = "weather empty!";
        }
         
        //"base"
        String result_base = jsonObject.getString("base");
         
        //"main"
        JSONObject JSONObject_main = jsonObject.getJSONObject("main");
        Double result_temp = JSONObject_main.getDouble("temp");
        Double result_pressure = JSONObject_main.getDouble("pressure");
        Double result_humidity = JSONObject_main.getDouble("humidity");
        Double result_temp_min = JSONObject_main.getDouble("temp_min");
        Double result_temp_max = JSONObject_main.getDouble("temp_max");
         
        //"wind"
        JSONObject JSONObject_wind = jsonObject.getJSONObject("wind");
        Double result_speed = JSONObject_wind.getDouble("speed");
        //Double result_gust = JSONObject_wind.getDouble("gust");
        Double result_deg = JSONObject_wind.getDouble("deg");
        String result_wind = "wind\tspeed: " + result_speed + "\tdeg: " + result_deg;
         
        //"clouds"
        JSONObject JSONObject_clouds = jsonObject.getJSONObject("clouds");
        int result_all = JSONObject_clouds.getInt("all");
         
        //"dt"
        int result_dt = jsonObject.getInt("dt");
         
        //"id"
        int result_id = jsonObject.getInt("id");
         
        //"name"
        String result_name = jsonObject.getString("name");
         
        //"cod"
        int result_cod = jsonObject.getInt("cod");
         
        return
            "Souřadnice: lon: " + result_lon + "lat: " + result_lat + "\n" +
            "Země: " + result_country + " Východ: " + result_sunrise + " Západ: " + result_sunset + "\n" +
            result_weather + "\n"+
            "Základna: " + result_base + "\n" +
            "Teplota: " + result_temp + "Vlhkost: " + result_humidity + "Tlak: " + result_pressure + "Min teplota: " + result_temp_min + "Max teplota: " + result_temp_min + "\n" +
            result_wind + "\n" +
            "Oblačnost: " + result_all + "\n" +
            "Název: " + result_name + "\n" +
            "Kód: " + result_cod + "\n" +
            "\n";
    }
}
