package main;

import ledger.AccountManager;
import ledger.Accountant;

public class Main {
	
	public static void main(String[] args) {
		
		AccountManager.init();
		Accountant accountant = new Accountant();
		
		AccountManager.printAccounts();
		
		for (int i = 0; i < 100; i++) {
			
			String entry = AccountManager.getRandomJournalEntry(6);
			System.out.println(entry);
			accountant.postEntry(entry);
		}	
		AccountManager.printAccounts();
	}

}
