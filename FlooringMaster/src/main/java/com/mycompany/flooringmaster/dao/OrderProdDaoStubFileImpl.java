/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dao;

import static com.mycompany.flooringmaster.dao.OrderProdDaoFileImpl.ORDERNUM_FILE;
import com.mycompany.flooringmaster.dto.Order;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ritheenhep
 */
public class OrderProdDaoStubFileImpl implements OrderDao {

    LocalDate todayDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    String formatDateTime = todayDate.format(formatter);
    String writeDate = formatter.format(todayDate);
    //key  value
    Map<Integer, Order> orderMap = new HashMap<>();// hasmap is now a Map// map is an interface. 

    int orderNumInc;

    public static final String ORDERNUM_FILE = "orderNumber.txt";
    public static final String DELIMITER = "::";

    public OrderProdDaoStubFileImpl() {
    }

    public OrderProdDaoStubFileImpl(LocalDate date) throws OrderPersistenceException {
       loadOrder(date);

    }

    private void writeOrder(LocalDate date) throws OrderPersistenceException, IOException {
        PrintWriter out;

        try {

            out = new PrintWriter(new FileWriter(getFilePath(date)));

        } catch (IOException ex) {
            throw new OrderPersistenceException("file");
        }

        List<Order> orderList = this.getAllOrder(date);

        for (Order currentOrderList : orderList) {
            out.println(currentOrderList.getOrderNumber() + DELIMITER
                    + currentOrderList.getCustomerName() + DELIMITER
                    + currentOrderList.getState() + DELIMITER
                    + currentOrderList.getTaxRate() + DELIMITER
                    + currentOrderList.getProductType() + DELIMITER
                    + currentOrderList.getArea() + DELIMITER
                    + currentOrderList.getCostPerSquareFoot() + DELIMITER
                    + currentOrderList.getLaborCostPerSquareFoot() + DELIMITER
                    + currentOrderList.getMeterialCost() + DELIMITER
                    + currentOrderList.getLaborCost() + DELIMITER
                    + currentOrderList.getTotalTax() + DELIMITER
                    + currentOrderList.getTotal());
//                    +currentOrderList.getOrderDate());

            out.flush();
        }

        out.close();
    }

    private void loadOrder(LocalDate date) throws OrderPersistenceException {
        String path = getFilePath(date);
        File f = new File(path);
        if (!f.exists()) {
            return;
        }

        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(path)));
        } catch (FileNotFoundException ex) {
            throw new OrderPersistenceException("");
        }
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setState(currentTokens[2]);
            currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
            currentOrder.setProductType(currentTokens[4]);
            currentOrder.setArea(new BigDecimal(currentTokens[5]));
            currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
            currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
            currentOrder.setMeterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTotalTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotal(new BigDecimal(currentTokens[11]));
            currentOrder.setOrderDate(date);

            orderMap.put(currentOrder.getOrderNumber(), currentOrder);

        }
        scanner.close();

    }

    private String getFilePath(LocalDate date) {
        return "order_" + date.format(formatter) + ".txt";
    }

    public void saveWork(LocalDate date)
            throws OrderPersistenceException, IOException {
        writeOrder(date);

    }

    @Override
    public void addOrder(Order order) throws OrderPersistenceException, IOException {
        totalNumber();
        writeFile();
        order.setOrderNumber(orderNumInc);
        orderMap.put(orderNumInc, order);

    }

    @Override
    public List<Order> getAllOrder(LocalDate date)
            throws OrderPersistenceException {
        loadOrder(date);
        List<Order> listDate = new ArrayList();
        List<Order> orderList = new ArrayList(orderMap.values());
        for (Order order : orderList) {
            if (order.getOrderDate().equals(date)) {
                listDate.add(order);

            }

        }
        return listDate;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws OrderPersistenceException {
//        loadOrder(date);
        List<Order> orderList = getAllOrder(date);
        Order getOrder = null;
        for (Order getAllOrders : orderList) {
            if (getAllOrders.getOrderNumber() == orderNumber) {
                getOrder = getAllOrders;
            }
        }
        return getOrder;
    }

    public void removeOrder(Order removeOrder) throws OrderPersistenceException {
//     
        orderMap.remove(removeOrder.getOrderNumber());
        
        try {
            writeOrder(removeOrder.getOrderDate());
        } catch (IOException ex) {
            Logger.getLogger(OrderProdDaoStubFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateOrder(Order order) throws OrderPersistenceException {
        orderMap.put(order.getOrderNumber(), order);
    }

    private void writeFile() throws OrderPersistenceException, IOException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ORDERNUM_FILE));
        } catch (IOException e) {
            throw new OrderPersistenceException("can't not write to file");
        }
        out.print(orderNumInc + 1);
        out.close();
    }

    //need try catch
    private void loadFile() throws FileNotFoundException, OrderPersistenceException {

        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(ORDERNUM_FILE)));
        } catch (IOException e) {
            throw new OrderPersistenceException(" cannot load file ");

        }
        orderNumInc = Integer.parseInt(sc.nextLine());
        sc.close();

    }

     public int totalNumber() throws FileNotFoundException, OrderPersistenceException {

        loadFile();

        return orderNumInc;

    }

    @Override
    public List<Order> getAllOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
