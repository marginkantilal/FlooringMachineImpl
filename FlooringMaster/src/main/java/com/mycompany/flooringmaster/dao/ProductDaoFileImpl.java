/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dao;

import com.mycompany.flooringmaster.dto.Order;
import com.mycompany.flooringmaster.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ritheenhep
 */
public class ProductDaoFileImpl implements ProductDao {

    private Map<String, Product> productMap = new HashMap<>();

    public static final String PRODUCT = "product.txt";
    public static final String DELIMITER = "::";

    private void loadProd() throws OrderPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT)));
        } catch (FileNotFoundException ex) {
            throw new OrderPersistenceException("");
        }
        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentProd = new Product(currentTokens[0]);
            currentProd.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentProd.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));
            productMap.put(currentProd.getProductType(), currentProd);
        }
        scanner.close();

    }

    public Product getProduct(String product) throws OrderPersistenceException {
        loadProd();
        return productMap.get(product);
    }

    public List<Product> getAllProduct() throws OrderPersistenceException {
        loadProd();
        List<Product> prodList = new ArrayList(productMap.values());

        return prodList;
    }

}
