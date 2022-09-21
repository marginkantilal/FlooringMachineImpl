/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.service;

import com.mycompany.flooringmaster.dao.StatesDao;
import com.mycompany.flooringmaster.dao.ProductDao;

import com.mycompany.flooringmaster.dao.OrderPersistenceException;
import com.mycompany.flooringmaster.dao.OrderProdDaoFileImpl;
//import com.mycompany.flooringmaster.dao.ProductDao;
import com.mycompany.flooringmaster.dao.ProductDaoFileImpl;
//import com.mycompany.flooringmaster.dao.StatesDao;
import com.mycompany.flooringmaster.dao.StatesDaoFileImpl;
import com.mycompany.flooringmaster.dto.Order;
import com.mycompany.flooringmaster.dto.Product;
import com.mycompany.flooringmaster.dto.States;
import java.lang.Thread.State;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.flooringmaster.dao.OrderDao;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ritheenhep
 */
public class OrderServiceImpl {

    Order order;
    States state;
    OrderDao orderDao;
    StatesDaoFileImpl statesDao;
    ProductDaoFileImpl prodDao;
    private String training;

    public OrderServiceImpl(OrderDao orderDao, StatesDaoFileImpl stateDao, ProductDaoFileImpl prodDao, String training) {
        this.orderDao = orderDao;
        this.statesDao = stateDao;
        this.prodDao = prodDao;
        this.training = training;
    }

    OrderServiceImpl(OrderDao dao, StatesDao state, ProductDaoFileImpl prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addOrder(Order order) throws OrderPersistenceException, IOException {
        validateOrderData(order);
        String stateName = order.getState();
        States state = statesDao.getStatesName(stateName);
        String productType = order.getProductType();
        Product product = prodDao.getProduct(productType);
        totalPrice(order, product, state);
        orderDao.addOrder(order);

//        orderDao.addOrder(order);
    }

    public List<Product> getAllProdType() throws OrderPersistenceException {

        return prodDao.getAllProduct();

    }

    public void updateOrder(Order order) throws OrderPersistenceException, OrderPersistenceException, OrderPersistenceException {

        String stateName = order.getState();
        States state = statesDao.getStatesName(stateName);
        String productType = order.getProductType();
        Product product = prodDao.getProduct(productType);

        totalPrice(order, product, state);

        orderDao.updateOrder(order);

    }

    public List<States> getAllState() throws OrderPersistenceException {
        return statesDao.getAllSates();
    }

    public List<Product> getAllProduct() throws OrderPersistenceException {
        return prodDao.getAllProduct();

    }

    public List<Order> getAllOrder(LocalDate date) throws OrderPersistenceException {
        
           
        return orderDao.getAllOrder(date);
    }
    

    public Order getOrder(LocalDate date, int orderNum) throws OrderPersistenceException {

        return orderDao.getOrder(date, orderNum);

    }

    public void removeOrder(Order removeOrder) throws OrderPersistenceException {

        orderDao.removeOrder(removeOrder);

    }

    public void saveWork() throws OrderPersistenceException, IOException {

        if (training.equals("Production")) {
            List< Order> orderDate = orderDao.getAllOrder();
            Set<LocalDate> uniqueDate = new HashSet<>();
            for(Order order : orderDate){
                uniqueDate.add(order.getOrderDate());          
            }
            for(LocalDate date : uniqueDate){
                 orderDao.saveWork (date);
            }
           
        }
    }

    //State s = statesDao.get(order.getState());
    //Product p = prodDao.get(order.getProductType());
    private void validateOrderData(Order order) throws OrderPersistenceException {
        if (order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0
                || order.getState() == null
                || order.getState().trim().length() == 0
                || order.getArea() == null || order.getArea().equals(0)) {
            throw new OrderPersistenceException("ERROR: all file required");

        }
    }

    public void totalPrice(Order order, Product product, States state) throws OrderPersistenceException {

        BigDecimal area = order.getArea();

        BigDecimal costpsqf = product.getCostPerSquareFoot();

        BigDecimal laborcspf = product.getLaborCostPerSquareFoot();

        BigDecimal taxRate = state.getStateTax();

        BigDecimal taxes = taxRate.divide(BigDecimal.valueOf(100));

        BigDecimal materialCostperSquareFeet = area.multiply(costpsqf);

        BigDecimal laborCostpersquarefoot = area.multiply(laborcspf);

        BigDecimal laborCost = materialCostperSquareFeet.add(laborCostpersquarefoot);

        BigDecimal tax = laborCost.multiply(taxes);

        BigDecimal total = tax.add(laborCost);

        BigDecimal totalScale = total.setScale(1, RoundingMode.HALF_UP);

        order.setCostPerSquareFoot(costpsqf);
        order.setLaborCostPerSquareFoot(laborCostpersquarefoot);
        order.setMeterialCost(materialCostperSquareFeet);
        order.setLaborCost(laborCost);
        order.setTaxRate(taxRate);
        order.setTotalTax(tax);
        order.setTotal(total);

    }

}
