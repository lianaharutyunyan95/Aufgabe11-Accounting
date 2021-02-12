package HW11.ledger;

import HW11.exceptions.AccountException;

import java.util.ArrayList;
import java.util.Random;

public class AccountManager {

    private static ArrayList<Asset> assets = new ArrayList<>();
    private static ArrayList<Liability> liabilities = new ArrayList<>();
    private static Random r = new Random();

    /**
     * Ratio from 0 to 100; 0 - (almost) no errors in journal entries, 100 - many errors in entries
     */
    private static final int ERRORRATIO = 10;

    public static Account getAccount(String name) throws AccountException {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.addAll(liabilities);
        accounts.addAll(assets);
        return accounts.stream()
                .filter(asset -> asset.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AccountException("Cannot find an account with name: " + name));
    }

    public static Account getAsset(String name) throws AccountException {
        return assets.stream()
                .filter(asset -> asset.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AccountException("Cannot find an asset with name: " + name));
    }

    public static Account getLiability(String name) throws AccountException {
        return liabilities.stream()
                .filter(asset -> asset.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AccountException("Cannot find an liability with name: " + name));
    }

    /**
     * Prints all accounts.
     */
    public static void printAccounts() {
        System.out.println("Assets:");
        for (Account a : assets) {
            System.out.println(" - " + a.toString());
        }
        System.out.println("Liabilities:");
        for (Account a : liabilities) {
            System.out.println(" - " + a.toString());
        }
    }

    /**
     * Inits Account Manager
     */
    public static void init() {
        //Debits
        assets.add(new Asset("Cash"));
        assets.add(new Asset("Inventory"));
        assets.add(new Asset("Supplies"));
        assets.add(new Asset("Land"));
        assets.add(new Asset("Equipment"));
        assets.add(new Asset("Vehicles"));
        assets.add(new Asset("Buildings"));
        //Revenues/Gains
        assets.add(new Asset("Operating_Revenues"));
        assets.add(new Asset("Other_Revenues"));

        //Credits
        liabilities.add(new Liability("Accounts_Payable"));
        liabilities.add(new Liability("Wages_Payable"));
        liabilities.add(new Liability("Interest_Payable"));
        liabilities.add(new Liability("Unearned_Payable"));
        liabilities.add(new Liability("Bonds_Payable"));
        liabilities.add(new Liability("Accounts_Payable"));
        liabilities.add(new Liability("Equity"));
        //Expenses/Losses
        liabilities.add(new Liability("Cost_of_Goods_Sold"));
        liabilities.add(new Liability("Payroll_Expenses"));
        liabilities.add(new Liability("Marketing_Expenses"));
        liabilities.add(new Liability("Other_Expenses"));
    }

    public static String getRandomJournalEntry() {
        return getRandomJournalEntry(r.nextInt(9) + 1);
    }

    public static String getRandomJournalEntry(int numberOfAffectedAccounts) {
        int d = r.nextInt(numberOfAffectedAccounts - 1) + 1;
        int c = numberOfAffectedAccounts - d;

        int value = 0;
        String entry = "";

        for (int i = 0; i < d; i++) {
            Account a = getRandomAccount();
            int v = r.nextInt(1000) + 1;


            if (r.nextInt(100) < ERRORRATIO) {
                value += v;
                if (r.nextInt(100) < 50) {
                    entry += a.getName() + " " + v + "* ";
                } else {
                    entry += a.getName() + "_NEW " + v + ", ";
                }
            } else {
                value += v;
                entry += a.getName() + " " + v + ", ";
            }
        }
        entry = entry.substring(0, entry.length() - 2);

        if (r.nextInt(100) < ERRORRATIO) {
            if (r.nextInt(100) < 50) {
                entry += "# ";
            } else {
                entry += " ";
            }
        } else {
            entry += "; ";
        }

        for (int i = 0; i < c - 1; i++) {
            Account a = getRandomAccount();
            int v = value / 3;

            value -= v;
            entry += a.getName() + " " + v + ", ";
        }

        Account a = getRandomAccount();
        int v = value;

        if (r.nextInt(100) < ERRORRATIO) {
            v++;
        }
        entry += a.getName() + " " + v;

        return entry;
    }

    private static Account getRandomAccount() {
        int i = r.nextInt(assets.size() + liabilities.size());
        if (i < assets.size()) {
            return assets.get(i);
        }
        return liabilities.get(i - assets.size());
    }
}
