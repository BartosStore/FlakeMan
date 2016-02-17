/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.bartos.flakeman.REST;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Míra
 */
@Stateless
public class JsonServiceImpl implements JsonService {

    public String getSimpleJsonResponse() {
        Client restClient = ClientBuilder.newClient();
        WebTarget webTarget = restClient.target("http://httpbin.org/ip");

        Response response = webTarget.request().get();
        String result = response.readEntity(String.class);
        return result;
    }

    //https://httpbin.org/get?show_env=1
    public String getJsonResponseWithParameter() {
        Client restClient = ClientBuilder.newClient();
        WebTarget webTarget = restClient.target("https://httpbin.org/get");
        webTarget = webTarget.queryParam("show_env", 1);

        Response response = webTarget.request().get();
        String result = response.readEntity(String.class);
        //String result = Integer.toString(response.getStatus());
        return result;
    }

    //api.openweathermap.org/data/2.5/weather?id=2172797&APPID=ccf69cbbc77d5c3d41888b81dc570cfb
    public String getWeatherResponse(String city) {
        Client restClient = ClientBuilder.newClient();
        WebTarget webTarget = restClient.target("http://api.openweathermap.org/data/2.5/weather");
        webTarget = webTarget.queryParam("q", city);
        webTarget = webTarget.queryParam("APPID", "ccf69cbbc77d5c3d41888b81dc570cfb");

        Response response = webTarget.request().get();
        String result = response.readEntity(String.class);
        return result;

    }

}
