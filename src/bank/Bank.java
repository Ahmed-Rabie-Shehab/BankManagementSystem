package bank;
import java.util.ArrayList;
import java.util.HashMap;



public class Bank {

   // private ArrayList<Account> accounts = new ArrayList<>();
    private HashMap<Integer, Account> accounts = new HashMap<>();


    public void addAccount(Account account) {
       // accounts.add(account);
        accounts.put(account.getAccountNumber(), account);
    }

    public double totalBalance() {
        double total = 0;
        //for (Account account : accounts) {
         for (Account account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }

    /*public Account findAccount(int accountNumber) {
      //  for (Account account : accounts) {
        for (Account account : accounts.values()) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
    public void printAll() {
        for (int i = 0; i <  accounts.size(); i++) {
            System.out.println(
                    accounts.get(i).getAccountNumber() + " | " +
                            accounts.get(i).getOwnerName() + " | " +
                            accounts.get(i).getBalance()
            );
        }
    }*/
    public void printAll() {
        for (Account account : accounts.values()) {
            System.out.println(
                    account.getAccountNumber() + " | " +
                            account.getOwnerName() + " | " +
                            account.getBalance()
            );
        }
    }
   /* public void removeAccount(int accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            accounts.remove(account);
        }
    }*/
    public Account findAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public void removeAccount(int accountNumber) {
        accounts.remove(accountNumber);
    }
}