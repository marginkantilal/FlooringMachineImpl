/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dto;

import java.math.BigDecimal;

/**
 *
 * @author ritheenhep
 */
public class States {
    
    private String stateName;
    BigDecimal stateTax;

    
    public States(String stateName) {
        this.stateName = stateName;
       
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getStateTax() {
        return stateTax;
    }

    public void setStateTax(BigDecimal stateTax) {
        this.stateTax = stateTax;
    }
    
    
    
    
    
}
