/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.ui;

import com.mycompany.flooringmaster.dao.OrderPersistenceException;
import com.mycompany.flooringmaster.dto.Order;
import com.mycompany.flooringmaster.dto.Product;
import com.mycompany.flooringmaster.dto.States;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ritheenhep
 */
public class OrderView {

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    String formatDateTime = now.format(formatter);
    UserIOImpl io;

    public OrderView(UserIOImpl io) {
        this.io = io;
    }

    public int displayMainMenu() {
        io.print("1. List order by date");
        io.print("2. Add order");
        io.print("3. Remove order");
        io.print("4. Edit order");
        io.print("5. Save Work");
        io.print("6. Exit");
        return io.readInt("Please select from above choices", 1, 6);
    }

    public Order getOrderInfo(List<States> states, List<Product> prods) throws OrderPersistenceException {
        boolean validState = false;
        boolean validProd = false;
        String orderState = "";
        String prodType = "";
        Order currentOrder = new Order();

        String orderName = io.readString("please enter Name");

        do {

            orderState = io.readString("plase enter your state ");

            for (States state : states) {

                if (orderState.equalsIgnoreCase(state.getStateName())) {

                    validState = true;
                }
            }

        } while (validState == false);

        do {

            prodType = io.readString("Please enter your material");
            
            for (Product prod : prods) {
                if (prodType.equalsIgnoreCase(prod.getProductType())) {
                   
                    validProd = true;
                }
            }
        } while (validProd == false);

        double area = io.readDouble("Please Enter the Area");

        currentOrder.setCustomerName(orderName);
        currentOrder.setState(orderState);
        currentOrder.setProductType(prodType);
        currentOrder.setArea(BigDecimal.valueOf(area));

        return currentOrder;

    }

    public void editOrder(Order order) {

        io.print("Current Name: " + order.getCustomerName() + "\n");
        String name = io.readString("Plase enter name");
        if (!name.isEmpty()) {
            order.setCustomerName(name);
        }

        io.print("Current State: " + order.getState() + "\n");
        String state = io.readString("Please enter State. ( MN OH etc)\n ");
        if (!state.isEmpty()) {
            order.setState(state);
        }

        io.print("Current Order: " + order.getProductType());
        String productType = io.readString("Please enter new product\n");
        if (!productType.isEmpty()) {
            order.setProductType(productType);
        }

        io.print("Current area: " + order.getArea() + "Sqf" + "\n");
        String area = io.readString("Please enter the area (sqf)\n");
        if (!area.isEmpty()) {
            order.setArea(new BigDecimal(area));
        }

    }

    public void displayProductType(List<Product> prodList) {
        io.println("Chose your material\n");
        for (Product currentProd : prodList) {
            io.println("Materail: "
                    + currentProd.getProductType()
                    + " | Cost PerSqaureFoot: "
                    + currentProd.getCostPerSquareFoot()
                    + " | Labor Cost PerSqaureFoot: "
                    + currentProd.getLaborCostPerSquareFoot());
        }

    }

    public int fineOrderNum() {
        int orderNum = io.readInt("Please enter order number\n");
        return orderNum;

    }

    public LocalDate findDate() {
        String orderDate = io.readString("Plea enter the date MMDDYYYY:\n");
        return LocalDate.parse(orderDate, formatter);
    }

//    public String displayEditOrder() {
//        io.print("");
//        return io.readString("Please enter order number ");
//    }
    public void displayOrder(List<Order> orderList) {

        for (Order currentOrder : orderList) {

            io.print("Order number" + currentOrder.getOrderNumber() + "\n Name: "
                    + currentOrder.getCustomerName() + "\n State: "
                    + currentOrder.getState() + "\n Material: "
                    + currentOrder.getProductType() + "\n Area: "
                    + currentOrder.getArea() + "\n Cost Per Squarefoot: "
                    + currentOrder.getCostPerSquareFoot() + "\n Labor Cost Per SquareFoot: "
                    + currentOrder.getLaborCostPerSquareFoot() + "\n Labor Cost: "
                    + currentOrder.getLaborCost()
                    + currentOrder.getMeterialCost() + "\n Tax Rate: "
                    + currentOrder.getTaxRate() + "\n Total Tax: "
                    + currentOrder.getTotalTax() + "\n Order Total Coast: "
                    + currentOrder.getTotal());
            io.print("=====================================");

        }

    }

    public void editOrderBanner() {
        io.print("====Edit Order====");

    }

    public void noOrderMessage() {
        io.print("No order found");
    }

    public void dislayOrderBanner() {
        io.print(" Add Order ");
    }

    public void errMessage(String errorMSG) {
        io.print("UnKnow Commad");
        io.print(errorMSG);
    }

}
