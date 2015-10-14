/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.bartos.flakeman.dao;

import cz.bartos.flakeman.domain.Brand;

/**
 *
 * @author miba
 */
public interface BrandDao {
    
    public Brand merge(Brand brand);
    
    public void save(Brand brand);
    
    public void delete(Brand brand);
    
    public Brand findBrandById(Brand brand);
    
}
