/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.bartos.flakeman.beans;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author miba
 */
@ManagedBean
@RequestScoped
public class ChartBean {

    private PieChartModel pieChartModel;
    
    @PostConstruct
    public void init() {
        createPieModel();
    }

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    private void createPieModel() {
        pieChartModel = new PieChartModel();
        pieChartModel.set("Skoda", 540);
        pieChartModel.set("Volskwagen", 325);
        pieChartModel.set("Hyundai", 702);
        pieChartModel.set("Kia", 421);
        
        pieChartModel.setTitle("Rozložení značek na trhu");
        pieChartModel.setLegendPosition("w");
        pieChartModel.setShowDataLabels(true);
    }
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
