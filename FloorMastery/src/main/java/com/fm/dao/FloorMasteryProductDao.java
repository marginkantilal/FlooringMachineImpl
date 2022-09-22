package com.fm.dao;

import java.util.List;

import com.fm.model.Product;
import com.fm.service.FlooringMasteryProductPersistenceException;

public interface FloorMasteryProductDao {

	List<Product> getAllProduct() throws FlooringMasteryProductPersistenceException;
    Product getProduct (String productType) throws FlooringMasteryProductPersistenceException;



}
