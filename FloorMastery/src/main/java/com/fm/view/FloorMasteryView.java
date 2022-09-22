package com.fm.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fm.model.Order;
import com.fm.service.FlooringMasteryOrderPersistenceException;
public class FloorMasteryView {

	private UserIO io;



    public FloorMasteryView(UserIO io) {
		this.io = io;
	}


	public FloorMasteryView(UserIOConsoleImpl io) {
        this.io = io;
    }


public char conformationOrder() {
	String ans;
    do{
        ans = io.readString("Would you like to place this order? (Y/N)");
    }while(!(((ans.toLowerCase().equals("n")) || (ans.toLowerCase().equals("y"))) && (ans.length() == 1)));

    return ans.charAt(0);
}

public void displayAddedOrderSuccess() {
    io.readString("Order successfuly added. Please hit enter to continue");
}


public void displayAddOrderBanner() {
    io.print("=== Add Order ===");
}

public void displayEditOrderBanner() {
    io.print("=== Edit Order ===");
}


public void displayError(Exception e){
        io.print(e.getMessage());
 }


public void displayErrorDate(){
    io.print("The date should be from today. Cannot accept past dates");
}


public void displayExportSuccessBanner(){
    io.readString("All order data has been exported. Please hit enter to continue");
}

public void displayFailedAddingOrder() {
    io.readString("The order has not been placed. Please hit enter to continue");
}

public int displayMenu() {
	io.print("* * * * * * * * * * * * * *");
	io.print("* <<Flooring Program>>    *");
	io.print("* 1. Display Orders       *");
	io.print("* 2. Add an Order         *");
	io.print("* 3. Edit an Order        *");
	io.print("* 4. Remove an Order      *");
	io.print("* 5. Export All Data      *");
	io.print("* 6. Quit                 *");
	io.print("* * * * * * * * * * * * * *");
	return io.readInt("Please enter a number from 1 to 6",+ 1, 6);

}


private void displayNotCustomerNameFoundBanner() {
	io.print("Customer doesn't exist with this name: ");
}


    public void displayOrder(Order order) {
	    if (order == null) {
	        io.print("No such order number.");
	
	    } else {
	        io.print(order.toString());
	    }
	    io.readString("Please hit enter to continue.");
	}

    public void displayOrderForAdding(Order order) {
	
		displayAddOrderBanner();
	    io.print(order.toStringForAddingOrder());
	
	     io.readString("Please hit enter to continue.");
	 }

    public void displayOrderFoundSuccess() {
        io.print("Order founded as shown below: ");
    }

    private void displayOrderListBanner() {
    	 io.print("Orders as shown below: ");
    }

    public void displayOrders(List<Order> orderList) {
	    if(!(orderList.isEmpty())) {
	    	displayOrderListBanner();
	    	  for(Order order : orderList){
	              io.print(order.toString());
	          }
	    }
	    	  else {
	    		  noFoundOrdersMessage();
	    	  }
	
	    io.readString("Please hit enter to continue.");
	
	    }
    private void displayProductTypeMenuBanner() {
	    io.print("=== Product Type Selection ===");
	}
    public void displayRemoveFailed() {
        io.print("Order remove failed");
    }
    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }


    public void displayRemoveSuccess(Order removedOrder) {
    	if(removedOrder != null){
            io.print("Order successfully removed");
        }else{
            io.print("No such order");
        }
        io.readString("Please hit enter to continue");
    }

    private void displayStateMenuBanner() {
		io.print("=== State Selection ===");
	
	}

    public void displayUnknownCommandMessage() {
		 io.print("Unknown Commad");
	}

    public void displayUpdateFailed() {
        io.print("Order update failed");
    }

    public void displayUpdateSuccess() {
        io.print("Order updated successfully");
    }
    public void exitMessage() {
        io.print("Thank you for using Floor Mastery.");
	}

	//Date formatter and validation
	public String getDate() {
		String date = io.readString("please enter date of order (yyyy-MM-dd)");
		return LocalDate.parse(date).format(DateTimeFormatter.ofPattern("MMddYYYY"));


	}

	 public Order getOrderInfo(List<String> states, List<String> prods) throws FlooringMasteryOrderPersistenceException {
	
	    Order order = new Order(io.readString("please enter customer name"));
	    displayStateMenuBanner();
	    for(int i = 0; i < states.size(); i++){
	        io.print((i+1) + ". " + states.get(i));
	    }
	    printSectionBanner();
	    int stateSelection = io.readInt("please choose your state (input number):  ", 1, states.size());
	    for(int i = 1; i <= states.size(); i++){
	        if(stateSelection == i){
	            order.setState(states.get(i-1));
	            break;
	        }
	    }
	    	displayProductTypeMenuBanner();
	    	for (int i = 0; i < prods.size(); i++) {
	            io.print((i+1) + ". " + prods.get(i));
	
			}
	        printSectionBanner();
	        //Available products
	        int productSelection = io.readInt("please choose your product (input number) ", 1, prods.size());
	        for(int i = 1; i <= prods.size(); i++){
	            if(productSelection == i){
	                order.setProductType(prods.get(i-1));
	                break;
	            }
	        }
	
	        order.setArea(new BigDecimal(Double.toString(io.readDouble("please enter area (100 minimum )", 100, 100000))));
	        return order;
	}

	public Order getOrderInfoForEditOrder(List<String> states, List<String> products, Order oldName) {
		//Alllowed to change CustomerNam, State, ProductType, Area
	
		displayEditOrderBanner();
	
		//gets and validate if the customer name exists
	    String newName = io.readString("Please enter customer's name (current: " + oldName.getCustomerName() + ")");
	    Order order;
	    if(!newName.isBlank() ||!(newName.length()==0) || !newName.isEmpty()) {
	        order = new Order(newName);
	    }
	    else {
	    	displayNotCustomerNameFoundBanner();
	        order = new Order(oldName.getCustomerName());
	    }
	
	    displayStateMenuBanner();
	    for (int i = 0; i < states.size(); i++) {
			io.print((i+1)+ ". " + states.get(i));
		}
	
	    printSectionBanner();
	
	
	    int state = io.readInt("Please choose state (current: " + oldName.getState() + ")", 1, states.size());
		for (int i = 1; i < states.size(); i++) {
			if(state == i) {
				order.setState(states.get(i-1));
				break;
			}
	
		}
		displayProductTypeMenuBanner();
		for (int i = 0; i < products.size(); i++) {
			io.print((i+1)+ ". " + products.get(i));
		}
	
	    printSectionBanner();
	
	
	    int productType = io.readInt("Please choose product (current: " + oldName.getProductType() + ")", 1, products.size());
		for (int i = 1; i < products.size(); i++) {
			if(productType == i && !products.isEmpty()) {
				order.setState(products.get(i-1));
				break;
			}
		}
	
	    order.setArea(new BigDecimal(Double.toString(io.readDouble("please enter area (100 minimum)(current area: " + oldName.getArea() + ")", 100, 100000))));
	
		//order.setArea(new BigDecimal(Double.toString(io.readDouble(newName))));
	    order.setOrderNumber(oldName.getOrderNumber());
		return order;
	
	}
	public int getOrderNum() {
	        return io.readInt("Please enter order number");
	}

	public void inValidDate() {
	        io.print("********************************************************");
	        io.print("ERROR: Invalid Date. Please enter date in correct format (yyyy-MM-dd)");
	        io.print("********************************************************");
	    }


	public void noFoundOrdersMessage() {
        io.print("No orders found in the List");
    }

	public void noOrderMessage() {
        io.print("No such as order found");
    }

	private void printSectionBanner() {
	    io.print("========================");
	
	}


	public char deleteConfirmationOrder() {
		String ans;
	    do{
	        ans = io.readString("Would you like to delete this order? (Y/N)");
	    }while(!(((ans.toLowerCase().equals("n")) || (ans.toLowerCase().equals("y"))) && (ans.length() == 1)));

	    return ans.charAt(0);
	
	}
}
