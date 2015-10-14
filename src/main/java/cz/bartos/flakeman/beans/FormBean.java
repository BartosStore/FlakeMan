/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.bartos.flakeman.beans;

import cz.bartos.flakeman.dao.BrandDao;
import cz.bartos.flakeman.domain.Brand;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author miba
 */
@Named
@ViewScoped
public class FormBean implements Serializable {

    @Inject
    private BrandDao brandDao;
    private Brand newBrand;

    @PostConstruct
    public void init() {
        newBrand = new Brand();
    }

    public String submitBrand() {
        brandDao.save(newBrand);

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Brand added!", "Your brand " + newBrand.getBrandName() + " was saved with country " + newBrand.getCountry());
        FacesContext.getCurrentInstance().addMessage(null, message);

        return "form";
    }

    public BrandDao getBrandDao() {
        return brandDao;
    }

    public void setBrandDao(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    public Brand getNewBrand() {
        return newBrand;
    }

    public void setNewBrand(Brand newBrand) {
        this.newBrand = newBrand;
    }

}
