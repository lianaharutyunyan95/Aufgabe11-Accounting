package HW11.ledger;

import HW11.exceptions.AccountException;
import HW11.exceptions.InvalidJournalEntryException;
import HW11.exceptions.InvalidSyntaxException;
import HW11.exceptions.UnequalBalanceException;
import HW11.util.Pair;

import java.util.ArrayList;

public class Accountant {

    public void postEntry(String journalEntry) {

        try {
            String[] debitAndCredit = checkSyntax(journalEntry);
            debitEqualsCredit(journalEntry);
            String debit = debitAndCredit[0];
            String credit = debitAndCredit[1];
            ArrayList<Pair<String, String>> debitPairs = getEntries(debit);
            ArrayList<Pair<String, String>> creditPairs = getEntries(credit);
            for (Pair<String, String> debitPair : debitPairs) {
                Account account = AccountManager.getAccount(debitPair.first);
                account.open();
            }
        } catch (InvalidJournalEntryException | AccountException e) {
            e.printStackTrace();
        }

        //Post entries

        //Ensure that all accounts are closed

    }

    private void debitEqualsCredit(String journalEntry) throws UnequalBalanceException {
        int debits = 0, credits = 0;
        String[] strings = journalEntry.split(";");
        for (int i = 0, stringsLength = strings.length; i < stringsLength; i++) {
            String entry = strings[i];
            String[] split = entry.split(",");
            if (i == 0) {
                debits = split.length;
            } else {
                credits = split.length;
            }
        }
        if (debits != credits) {
            throw new UnequalBalanceException("Credits and Debits are not equal: " + journalEntry);
        }
    }

    private String[] checkSyntax(String journalEntry) throws InvalidJournalEntryException {
        String[] strings = journalEntry.split(";");
        if (strings.length != 2) {
            throw new InvalidSyntaxException("Wrong number of entries: " + journalEntry);
        }
        return strings;
    }

    private ArrayList<Pair<String, String>> getEntries(String journalEntry) {
        ArrayList<Pair<String, String>> pairs = new ArrayList<>();
        String[] accounts = journalEntry.split(", ");
        for (String account : accounts) {
            String[] s = account.split(" ");
            Pair<String, String> pair = new Pair<>(s[0], s[1]);
            pairs.add(pair);
        }
        return pairs;
    }
}
