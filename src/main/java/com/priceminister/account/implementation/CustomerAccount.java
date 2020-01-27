package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

	private double balance;
	
    public CustomerAccount() {
    	this.balance = 0.0;
    }
    
	public CustomerAccount(double balance) {
		this.balance = balance;
	}

	public void add(Double addedAmount) throws IllegalArgumentException {
		
		if (addedAmount <= 0.0) {
			throw new IllegalArgumentException("Illegal " + addedAmount); // autoboxing
		}
		this.balance+= addedAmount;
	}

    public Double getBalance() {
        return balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException {
    	final Double accountBalance = this.balance - withdrawnAmount;
    	
    	if (!rule.withdrawPermitted(accountBalance)) {
			 throw new IllegalBalanceException(accountBalance);
		}
    	this.balance = accountBalance;
        return this.balance;
    }

}
