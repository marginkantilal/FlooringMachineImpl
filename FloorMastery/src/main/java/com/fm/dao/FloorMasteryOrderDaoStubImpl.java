//package com.fm.dao;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//import com.fm.model.Order;
//
//public class FloorMasteryOrderDaoStubImpl implements FloorMasteryOrderDao{
//
//	 	public static final String ORDERNUM_FILE = "orderNumber.txt";
//	    public static final String DELIMITER = ",";
//	 	LocalDate todayDate = LocalDate.now();
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
//	    String formatDateTime = todayDate.format(formatter);
//	    String writeDate = formatter.format(todayDate);
//
//	    Map<Integer, Order> orders = new HashMap<>();// hasmap is now a Map// map is an interface.
//
//	    int orderNumInc;
//	   public  FloorMasteryOrderDaoStubImpl(LocalDate date) throws FlooringMasteryOrderPersistenceException{
//	    	loadOrder(date);
//	    }
//
//
//	   private void loadOrder(LocalDate date) throws FlooringMasteryOrderPersistenceException {
//	        String path = getFilePath(date);
//	        File f = new File(path);
//	        if (!f.exists()) {
//	            return;
//	        }
//
//	        Scanner scanner = null;
//
//	        try {
//	            scanner = new Scanner(new BufferedReader(new FileReader(path)));
//	        } catch (FileNotFoundException ex) {
//	            throw new FlooringMasteryOrderPersistenceException("");
//	        }
//	        String currentLine;
//	        String[] currentTokens;
//	        while (scanner.hasNextLine()) {
//	            currentLine = scanner.nextLine();
//	            currentTokens = currentLine.split(DELIMITER);
//	            Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
//	            currentOrder.setCustomerName(currentTokens[1]);
//	            currentOrder.setState(currentTokens[2]);
//	            currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
//	            currentOrder.setProductType(currentTokens[4]);
//	            currentOrder.setArea(new BigDecimal(currentTokens[5]));
//	            currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
//	            currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
//	            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
//	            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
//	            currentOrder.setTax(new BigDecimal(currentTokens[10]));
//	            currentOrder.setTotal(new BigDecimal(currentTokens[11]));
//	            currentOrder.setOrderDate(date);
//
//	            orders.put(currentOrder.getOrderNumber(), currentOrder);
//
//	        }
//	        scanner.close();
//
//	    }
//
//
//	   private String getFilePath(LocalDate date) {
//	        return "order_" + date.format(formatter) + ".txt";
//	    }
//	   public void saveWork(LocalDate date)
//	            throws FlooringMasteryOrderPersistenceException, IOException {
//	        writeOrder(date);
//
//	    }
//
//
//	   private void writeOrder(LocalDate date) throws FlooringMasteryOrderPersistenceException, IOException {
//	        PrintWriter out;
//
//	        try {
//
//	            out = new PrintWriter(new FileWriter(getFilePath(date)));
//
//	        } catch (IOException ex) {
//	            throw new FlooringMasteryOrderPersistenceException("file not found");
//	        }
//
//	        List<Order> orderList = this.getAllOrders(date);
//
//	        for (Order currentOrderList : orderList) {
//	            out.println(currentOrderList.getOrderNumber() + DELIMITER
//	                    + currentOrderList.getCustomerName() + DELIMITER
//	                    + currentOrderList.getState() + DELIMITER
//	                    + currentOrderList.getTaxRate() + DELIMITER
//	                    + currentOrderList.getProductType() + DELIMITER
//	                    + currentOrderList.getArea() + DELIMITER
//	                    + currentOrderList.getCostPerSquareFoot() + DELIMITER
//	                    + currentOrderList.getLaborCostPerSquareFoot() + DELIMITER
//	                    + currentOrderList.getMaterialCost() + DELIMITER
//	                    + currentOrderList.getLaborCost() + DELIMITER
//	                    + currentOrderList.getTax() + DELIMITER
//	                    + currentOrderList.getTotal());
////	                    +currentOrderList.getOrderDate());
//
//	            out.flush();
//	        }
//
//	        out.close();
//	    }
//	@Override
//	public void removeOrder(Date orderDate, int orderNo) throws FlooringMasteryOrderPersistenceException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public List<Order> getAllOrders(LocalDate date) throws FlooringMasteryOrderPersistenceException {
//		loadOrder(date);
//        List<Order> listDate = new ArrayList();
//        List<Order> orderList = new ArrayList(orders.values());
//        for (Order order : orderList) {
//            if (order.getOrderDate().equals(date)) {
//                listDate.add(order);
//
//            }
//
//        }
//        return listDate;
//	}
//
//	@Override
//	public void editOrderDetails(Order newOrder) throws FlooringMasteryOrderPersistenceException {
//
//        orders.put(newOrder.getOrderNumber(), newOrder);
//
//	}
//
//	@Override
//	public void addOrder(Order order) throws FlooringMasteryOrderPersistenceException, IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//
//	@Override
//	public List<Order> getAllOrders() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//
//	}
//
//
//	@Override
//	public Order getOrder(LocalDate date, int orderNum) throws FlooringMasteryOrderPersistenceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public void removeOrder(Order removeOrder) throws FlooringMasteryOrderPersistenceException {
//		// TODO Auto-generated method stub
//
//	}
//
//}
