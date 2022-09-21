/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.dao;

import com.mycompany.flooringmaster.dto.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ritheenhep
 */

public interface ProductDao {
    
    Product getProduct(String product) throws OrderPersistenceException;
  

    List<Product> getAllProduct() throws OrderPersistenceException;
    
   

}
