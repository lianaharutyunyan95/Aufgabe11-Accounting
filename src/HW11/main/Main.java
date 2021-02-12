package HW11.main;

import HW11.ledger.AccountManager;
import HW11.ledger.Accountant;

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

//	public static void HW11.main(String[] args) {
//		String entry = "Buildings 799, Land 213, Accounts_Payable 446; Equipment 486, Cost_of_Goods_Sold 324, Equity 648";
//		String[] debitAndCredit = entry.split(";");
//		String debit = debitAndCredit[0];
//		String[] accounts = debit.split(", ");
//		for (String account : accounts) {
//
//		}
//	}

}
