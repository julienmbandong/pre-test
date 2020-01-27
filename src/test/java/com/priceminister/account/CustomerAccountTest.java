package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.implementation.*;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount(1000.0); // Initialization by instatiation.
        rule =  new CustomerAccountRule();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
    	customerAccount = new CustomerAccount();
    	final Double balance = customerAccount.getBalance(); 
    	assertEquals((Double) 0.0, balance);
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
    	final Double addedAmount =  1000.0;
    	final Double expectedAccountBalance = customerAccount.getBalance() + addedAmount;
    	customerAccount.add(addedAmount);
    	final Double actualAccountBalance = customerAccount.getBalance();
    	assertEquals(expectedAccountBalance, actualAccountBalance);
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     * @throws IllegalBalanceException 
     */
    @Test(expected = IllegalBalanceException.class)
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
    	final Double withdrawAmount = 1100.0;  // 1100.0 > 1000.0
    	
    	customerAccount.withdrawAndReportBalance(withdrawAmount, rule);
    	
    }
    
    // Also implement missing unit tests for the above functionalities.
    /**
     * Test All whole withdrawal
     * @throws IllegalBalanceException
     */
    @Test
    public void testNormalWithdrawEqualToBalance() throws IllegalBalanceException {
    	
    	final Double withdrawAmount = 1000.0;
    	final Double expectedBalance = customerAccount.getBalance() - withdrawAmount; // 0.0
    	customerAccount.withdrawAndReportBalance(withdrawAmount, rule);  
    	final Double actualBalance = customerAccount.getBalance(); // 0.0
    	assertEquals(expectedBalance, actualBalance);  	
    }
    
    /**
     * Adds negative amount to the account. Unauthorized operation and expect IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNegativeAmountToAccountReportIllegalArgument() {
    	
    	final Double expectedBalance = customerAccount.getBalance(); // 1000.0
    	final Double amount = -1000.0; 
    	customerAccount.add(amount); // no valide operation
    	final Double actualBalance = customerAccount.getBalance(); // 0.0
    	
    	assertEquals(expectedBalance, actualBalance); // will generate in every cases an illegalArgumentException
    }
    
    /**
     * Test Small withdrawal
     * @throws IllegalBalanceException
     */
    @Test
    public void testSmallWithdrawAndReportBalance() throws IllegalBalanceException {
    	final Double withdrawAmount = 400.0; 
    	final Double expectedBalance = customerAccount.getBalance() - withdrawAmount; // 600.0
    	customerAccount.withdrawAndReportBalance(withdrawAmount, rule); // 600.0
    	final Double actualBalance = customerAccount.getBalance();
    	assertEquals(expectedBalance, actualBalance); // expectedBalance > 0
    }
    
    /**
     * Adds zero amount to the account. Unauthorized operation and expect IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddZeroAmountToAccountReportIllegalArgument() {
    	final Double amount = 0.0;
    	final Double expectedBalance = customerAccount.getBalance(); // 1000.0
    	customerAccount.add(amount);
    	final Double actualBalance = customerAccount.getBalance(); // 1000.0
    	assertEquals(expectedBalance, actualBalance);
    }

}
