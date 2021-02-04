package ledger;

import exceptions.InvalidJournalEntryException;
import exceptions.InvalidSyntaxException;
import exceptions.UnequalBalanceException;
import util.Pair;

import java.util.ArrayList;

public class Accountant {

    public void postEntry(String journalEntry) {

        try {
            String[] debitAndCredit = checkSyntax(journalEntry);
            debitEqualsCredit(journalEntry);
            String debit = debitAndCredit[0];
            String credit = debitAndCredit[1];
        } catch (InvalidJournalEntryException e) {
            e.printStackTrace();
        }
        //Open Accounts


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

//    private ArrayList<Pair<String, Integer>> getEntries(String journalEntry) {
//
//    }
}
