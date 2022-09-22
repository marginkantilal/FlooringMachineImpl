package com.fm.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class States {

	private String StateAbbreviation;
	private String StateName;
	private BigDecimal taxRate;


	public States(String StateAbbreviation) {
        this.StateAbbreviation = StateAbbreviation;
    }

	public States(String stateAbbreviation, String stateName, BigDecimal taxRate) {
		StateAbbreviation = stateAbbreviation;
		StateName = stateName;
		this.taxRate = taxRate;
	}



	@Override
	public String toString() {
		return "States {StateAbbreviation=" + StateAbbreviation + ", StateName=" + StateName + ", taxRate=" + taxRate
				+ "}";
	}



}
