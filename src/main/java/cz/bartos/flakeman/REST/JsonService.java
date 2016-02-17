/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.bartos.flakeman.REST;

/**
 *
 * @author Míra
 */
public interface JsonService {

    public String getSimpleJsonResponse();

    public String getJsonResponseWithParameter();

    public String getWeatherResponse(String city);

}
