package bank;

public class Bank {

    private Account[] accounts;
    private int size;

    public Bank(int capacity) {
        accounts = new Account[capacity];
    }

    public boolean addAccount(Account account) {
        if (size == accounts.length) {
            return false;
        }
        accounts[size] = account;
        size++;
        return true;
    }

    public double totalBalance() {
        double total = 0;
        for (int i = 0; i < size; i++) {
            total += accounts[i].getBalance();
        }
        return total;
    }
}