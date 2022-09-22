package com.fm.app;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fm.controller.FlooringMasteryController;
import com.fm.service.FlooringMasteryOrderPersistenceException;
import com.fm.service.FlooringMasteryProductPersistenceException;
import com.fm.service.FlooringMasteryStatePersistenceException;


public class App {
    public static void main(String[] args) throws FlooringMasteryOrderPersistenceException, FlooringMasteryProductPersistenceException, FlooringMasteryStatePersistenceException, IOException {





    		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContent.xml");
    		FlooringMasteryController controller = context.getBean("controller", FlooringMasteryController.class);

    	    controller.run();


    }
}
