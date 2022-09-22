package com.fm.dao;

import java.math.BigDecimal;
import java.util.List;

import com.fm.model.States;
import com.fm.service.FlooringMasteryStatePersistenceException;

public interface FloorMasteryStatesTaxDao {
	public List<States> getAllStates() throws FlooringMasteryStatePersistenceException;


	 States getStatesName(String states) throws FlooringMasteryStatePersistenceException;


     States getStateTax(BigDecimal tax) throws FlooringMasteryStatePersistenceException;

}
