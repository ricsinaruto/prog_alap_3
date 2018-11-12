package junitlab.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;

public class BankTest {
	
	GreatSavingsBank bank;
	
	@Before
	public void setUp() {
		bank = new GreatSavingsBank();
	}

	@Test
	public void testOpenAccount() throws AccountNotExistsException {
		String acc_number = bank.openAccount();
		assertNotNull(null, acc_number);
		assertEquals(0, bank.getBalance(acc_number));
	}
	
	@Test
	public void testUniqueAccount() {
		String acc_number1 = bank.openAccount();
		String acc_number2 = bank.openAccount();
		
		assertNotSame(acc_number1, acc_number2);
	}
	
	@Test(expected=junitlab.bank.AccountNotExistsException.class)
	public void testInvalidAccound() throws AccountNotExistsException {
		bank.getBalance("hahaha");
	}
	
	@Test
	public void testDeposit() throws AccountNotExistsException {
		String acc_number = bank.openAccount();
		bank.deposit(acc_number, 2000);
		assertEquals(2000, bank.getBalance(acc_number));
	}

}
