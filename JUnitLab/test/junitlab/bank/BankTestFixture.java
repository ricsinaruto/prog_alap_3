package junitlab.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;

public class BankTestFixture {
	
	GreatSavingsBank bank;
	String acc_number1;
	String acc_number2;
	
	@Before
	public void setUp() throws AccountNotExistsException {
		bank = new GreatSavingsBank();
		
		acc_number1 = bank.openAccount();
		bank.deposit(acc_number1, 1500);
		
		acc_number2 = bank.openAccount();
		bank.deposit(acc_number2, 12000);
	}

	@Test
	public void testTransfer() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(acc_number2, acc_number1, 3456);
		
		assertEquals(bank.getBalance(acc_number1), 4956);
		assertEquals(bank.getBalance(acc_number2), 8544);
	}
	
	@Test
	public void testClose() throws AccountNotExistsException {
		boolean closed = bank.closeAccount(acc_number1);
		assertEquals(closed, false);
		
		String new_acc = bank.openAccount();
		boolean closed2 = bank.closeAccount(new_acc);
		assertEquals(closed2, true);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegDep() throws AccountNotExistsException {
		bank.deposit(acc_number1, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTransferError() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(acc_number2, acc_number1, -1);
	}
	
	@Test(expected=junitlab.bank.NotEnoughFundsException.class)
	public void testTransferWithoutEnoughFunds() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(acc_number1, acc_number2, 3456);
	}

}
