package com.fm.service;

import java.io.IOException;
import java.util.List;

import com.fm.model.Order;

public interface FlooringMasteryService {

	Order addOrder (Order order, String orderDate)throws FlooringMasteryOrderPersistenceException, FlooringMasteryStatePersistenceException, FlooringMasteryProductPersistenceException;
	Order editOrderDetails(int orderNumber, Order newOrder, String date) throws FlooringMasteryOrderPersistenceException, FlooringMasteryStatePersistenceException, FlooringMasteryProductPersistenceException;
	void exportOrderData() throws FlooringMasteryOrderPersistenceException;
	List<Order> getAllOrders(String date)throws FlooringMasteryOrderPersistenceException;
	Order getOrder(int orderNumber, String date) throws FlooringMasteryOrderPersistenceException;
	List<String>getProductList() throws FlooringMasteryProductPersistenceException;
	List<String> getStatesList() throws FlooringMasteryStatePersistenceException;
	void orderTotalPricingCal(Order order)throws FlooringMasteryOrderPersistenceException, FlooringMasteryStatePersistenceException, FlooringMasteryProductPersistenceException;
	Order removeOrder(int orderNumber, String orderDate) throws FlooringMasteryOrderPersistenceException, IOException;



}
