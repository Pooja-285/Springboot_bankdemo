package com.demo.bank.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Amount {
	
	private Double amount;
	
	public Amount() {
		this.amount = null;
	}

}
