package com.fm.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fm.model.Order;
import com.fm.model.Product;
import com.fm.model.States;
import com.fm.service.FlooringMasteryOrderPersistenceException;
import com.fm.service.FlooringMasteryProductPersistenceException;
import com.fm.service.FlooringMasteryStatePersistenceException;

public interface FloorMasteryOrderDao {


		Order addOrder(Order order, String path) throws FlooringMasteryOrderPersistenceException;
	    Order editOrder(Order newOrder, String path) throws FlooringMasteryOrderPersistenceException;

	    void exportOrder(String path) throws FlooringMasteryOrderPersistenceException;
	    List<Order> getAllOrder(String path) throws FlooringMasteryOrderPersistenceException;
	    Order getOrder(int orderNumber, String path) throws FlooringMasteryOrderPersistenceException;

	    Map<String, Product> productLog() throws FlooringMasteryProductPersistenceException;
	    Order removeOrder(int orderNumber, String path) throws FlooringMasteryOrderPersistenceException, IOException;
	    Map<String, States> stateLog() throws FlooringMasteryStatePersistenceException;
}
