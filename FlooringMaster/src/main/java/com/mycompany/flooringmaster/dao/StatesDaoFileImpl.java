/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dao;

import static com.mycompany.flooringmaster.dao.ProductDaoFileImpl.DELIMITER;
import com.mycompany.flooringmaster.dto.Order;

import com.mycompany.flooringmaster.dto.Product;
import com.mycompany.flooringmaster.dto.States;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ritheenhep
 */
public class StatesDaoFileImpl implements StatesDao {

    Map<String, States> statesMap = new HashMap<>();

    private static final String STATE_FILE = "state.txt";

    public StatesDaoFileImpl() {
    }

    private void loadStatesNTax() throws OrderPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader((STATE_FILE))));
        } catch (FileNotFoundException ex) {
            throw new OrderPersistenceException("");
        }
        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            States currentState = new States(currentTokens[0]);
            currentState.setStateTax(new BigDecimal(currentTokens[1]));
            statesMap.put(currentState.getStateName(), currentState);

        }
        scanner.close();
    }

    public States getStatesName(String states) throws OrderPersistenceException {
        loadStatesNTax();
        return statesMap.get(states);
    }

    public States getStateTax(BigDecimal tax) throws OrderPersistenceException {
        loadStatesNTax();
        return statesMap.get(tax);
    }

    public List<States> getAllSates()
            throws OrderPersistenceException {
        loadStatesNTax();

        List<States> states = new ArrayList(statesMap.values());

        return states;

    }

}
