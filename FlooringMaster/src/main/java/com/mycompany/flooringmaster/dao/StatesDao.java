/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dao;

import com.mycompany.flooringmaster.dto.States;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ritheenhep
 */
public interface StatesDao {
    
     States getStatesName(String states) throws OrderPersistenceException;
    
  
     States getStateTax(BigDecimal tax) throws OrderPersistenceException;
  
    public List<States> getAllSates()
            throws OrderPersistenceException;
}
