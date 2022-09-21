/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.controller;

import com.mycompany.flooringmaster.dao.OrderPersistenceException;
import com.mycompany.flooringmaster.dao.StatesDaoFileImpl;

import com.mycompany.flooringmaster.dto.Order;
import com.mycompany.flooringmaster.dto.Product;
import com.mycompany.flooringmaster.dto.States;
import com.mycompany.flooringmaster.service.OrderServiceImpl;
import com.mycompany.flooringmaster.ui.OrderView;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ritheenhep
 */
public class OrderController {
    Product prod;
    StatesDaoFileImpl state;
    OrderServiceImpl service;
    OrderView view;

    public OrderController(OrderServiceImpl service, OrderView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        boolean runs = true;
        int menuSelection = 0;

        try {

            while (runs) {

                menuSelection = getMainMenu();
                switch (menuSelection) {
                    case 1:        
                        displayOrderByDate();
                        break;
                    case 2:
                        listProdType();
                        addOrder();
                         listOrder();
                        break;
                    case 3:
                        removeOrder();
                        break;
                    case 4:
                        editOrder();
                        
                        break;
                    case 5:
                        saveWork();
                        break;
                    case 6:
                        runs = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (IOException | OrderPersistenceException e) {
            view.errMessage(e.getMessage());
           
        }

    }
    
    
       private void addOrder() throws OrderPersistenceException {
          List<States> states = service.getAllState();
          List<Product> products = service.getAllProduct();
        view.dislayOrderBanner();
        Order order = view.getOrderInfo(states, products);
        try {
            service.addOrder(order);
        } catch (IOException ex) {
        }
    }
       
       
       private void displayOrderByDate() throws OrderPersistenceException{
     
        List<Order> order =  service.getAllOrder(view.findDate());
        
        if(order.isEmpty()){
            view.noOrderMessage(); 
        }else{     
            
       view.displayOrder(order);
           
        }
           
           
       }

    private void editOrder() throws OrderPersistenceException {
        
        int orderNum = view.fineOrderNum();
        Order order = service.getOrder(view.findDate(), orderNum);
        if(order==null){
            view.noOrderMessage(); 
        }else{      
               view.editOrder(order);
            service.updateOrder(order);
        }
    
    }
 
    public int getMainMenu() {
      
        return view.displayMainMenu();

    }

    private void listOrder() throws OrderPersistenceException { 
          LocalDate date = LocalDate.now();
        List<Order> list = service.getAllOrder(date);
        view.displayOrder(list);

    }
 
   
    private void listProdType() throws OrderPersistenceException {
        List prod = service.getAllProdType();
        view.displayProductType(prod);

    }

    private void saveWork() throws OrderPersistenceException, IOException {  
            service.saveWork();
       
    }

    private void removeOrder() throws OrderPersistenceException {
         int orderNum = view.fineOrderNum();
        Order order = service.getOrder(view.findDate(), orderNum);
        if(order==null){
            view.noOrderMessage(); 
        }else{      
            service.removeOrder(order);
            
        }
    
    }

    private void unknownCommand() {

    }

 
}
