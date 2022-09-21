/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dao;

import com.mycompany.flooringmaster.dto.Order;
import com.mycompany.flooringmaster.dto.Product;
import com.mycompany.flooringmaster.dto.States;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;

/**
 *
 * @author ritheenhep
 */
public interface OrderDao {

    List<Order> getAllOrder(LocalDate date) throws OrderPersistenceException;

    void addOrder(Order order) throws OrderPersistenceException, IOException;

    void removeOrder(Order order)throws OrderPersistenceException;

    Order getOrder(LocalDate date, int orderNumber) throws OrderPersistenceException;
    
    void updateOrder(Order order)throws OrderPersistenceException;
    
    int totalNumber() throws FileNotFoundException, OrderPersistenceException;

    void saveWork(LocalDate date) throws OrderPersistenceException, IOException;
    
    List<Order> getAllOrder();
}
