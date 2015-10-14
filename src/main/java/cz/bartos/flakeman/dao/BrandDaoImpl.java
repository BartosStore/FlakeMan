/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.bartos.flakeman.dao;

import cz.bartos.flakeman.domain.Brand;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miba
 */
@Stateless
public class BrandDaoImpl implements BrandDao {

    @PersistenceContext
    EntityManager manager;

    @Override
    public Brand merge(Brand brand) {
        return manager.merge(brand);
    }

    @Override
    public void save(Brand brand) {
        manager.persist(brand);
    }

    @Override
    public void delete(Brand brand) {
        manager.remove(brand);
    }

    @Override
    public Brand findBrandById(Brand brand) {
        return manager.find(Brand.class, brand);
    }

}
