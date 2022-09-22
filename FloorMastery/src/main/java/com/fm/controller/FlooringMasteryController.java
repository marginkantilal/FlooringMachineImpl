package com.fm.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fm.model.Order;
import com.fm.service.FlooringMasteryOrderPersistenceException;
import com.fm.service.FlooringMasteryProductPersistenceException;
import com.fm.service.FlooringMasteryService;
import com.fm.service.FlooringMasteryStatePersistenceException;
import com.fm.view.FloorMasteryView;

public class FlooringMasteryController {


    private FlooringMasteryService service;
    private FloorMasteryView view;



	public FlooringMasteryController(FlooringMasteryService service, FloorMasteryView view) {
		super();
		this.service = service;
		this.view = view;
	}


private void addOrder() throws FlooringMasteryOrderPersistenceException, FlooringMasteryStatePersistenceException, FlooringMasteryProductPersistenceException, IOException {
	  view.displayAddOrderBanner();
	   List<String> states = service.getStatesList();
       List<String> products = service.getProductList();

	  Order order = view.getOrderInfo(states, products);

	  String date = null;
	  while(true) {
      try {
          date = view.getDate();

      } catch (Exception e) {
    	  view.inValidDate();
      }

      if(date.compareTo(LocalDate.now().format(DateTimeFormatter.ofPattern("MMDDYYYY")))<0) {
      	view.displayErrorDate();
      	continue;
	  }
	  break;
}
		view.displayOrderForAdding(order);

		char ch = view.conformationOrder();
        if(ch == 'n' && !(ch =='y')){
        	 view.displayFailedAddingOrder();

        } else{
        	service.addOrder(order, date);
            view.displayAddedOrderSuccess();
        }



}


public void displayError(Exception e){
    view.displayError(e);
}

private void displayExitMessage() {
	view.exitMessage();
}


private void displayOrders() throws FlooringMasteryOrderPersistenceException {
	String date = null;
	while (true) {
		try {
		date = view.getDate();
		break;
	}
		catch(Exception e) {
			view.inValidDate();
		}
	}


	List<Order> order =  service.getAllOrders(date);
    view.displayOrders(order);

}

private void editOrderDetails() throws FlooringMasteryStatePersistenceException, FlooringMasteryProductPersistenceException, FlooringMasteryOrderPersistenceException {
    view.displayEditOrderBanner();
	int orderNum = view.getOrderNum();
	List<String> stateList = service.getStatesList();
	List<String> productList = service.getProductList();
	String date = null;
	while(true){
		try {
			date = view.getDate();
			break;
		}catch(Exception e) {
			view.inValidDate();
		}
	}

	Order oldOrder = service.getOrder(orderNum, date);
	view.displayOrder(oldOrder);
	if(oldOrder == null) {
		view.noFoundOrdersMessage();
	}else {
		Order order = view.getOrderInfoForEditOrder(stateList, productList, oldOrder);
		System.out.println(order.toString());
        service.editOrderDetails(orderNum, order, date);
        view.displayUpdateSuccess();


	}
}



private void exportOrderData() throws FlooringMasteryStatePersistenceException, FlooringMasteryOrderPersistenceException {

	        service.exportOrderData();
	        view.displayExportSuccessBanner();
	    }


//Receive menu selection from user
private int getMenuSelection() {
	return view.displayMenu();
}



private void removeOrder() throws FlooringMasteryOrderPersistenceException, IOException {
	view.displayRemoveOrderBanner();
	int orderNum = view.getOrderNum();
	String date = null;

	while(true) {
		try {
			date = view.getDate();
			break;
		}
		catch(Exception e) {
			view.inValidDate();
		}
	}


    Order oldOrder = service.getOrder(orderNum, date);
    if(oldOrder==null){
        view.noOrderMessage();
    }char ch = view.deleteConfirmationOrder();
    if(ch == 'y'){
        Order removedOrder = service.removeOrder(orderNum, date);
        view.displayRemoveSuccess(removedOrder);
    } else{
        view.displayRemoveFailed();
    }
}
public void run() throws FlooringMasteryOrderPersistenceException, FlooringMasteryProductPersistenceException, FlooringMasteryStatePersistenceException, IOException {
    boolean flag = true;
	int menuSelection = 0;


	while (flag) {
		menuSelection = getMenuSelection();
		switch (menuSelection) {
		case 1:
			{
				try{
					displayOrders();
			}
				catch(FlooringMasteryOrderPersistenceException ex) {
				displayError(ex);
			}
		}
			break;
		case 2:

		{
			try {
				addOrder();
			}
			catch (FlooringMasteryOrderPersistenceException ex) {
				displayError(ex);
			}
		}
		break;
		case 3:
		{
			try {
				editOrderDetails();
			}
			catch(FlooringMasteryOrderPersistenceException ex){
                displayError(ex);

			}
		}
			break;
		case 4:
		{
			try {
				removeOrder();
			}
			catch(FlooringMasteryOrderPersistenceException ex){
                displayError(ex);

			}
		}
			break;
		case 5:
		{
			exportOrderData();
		}
			break;
		case 6:
			flag = false;
			break;
		default:
			unknownCommand();
		}
	}
	displayExitMessage();

}

private void unknownCommand() {
	view.displayUnknownCommandMessage();
}

}
