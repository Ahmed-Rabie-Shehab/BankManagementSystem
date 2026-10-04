package bank;

public abstract class Account {

    private int accountNumber;
    private String ownerName;
    protected double balance;
    private static int accountsCount = 0;

    public Account(String ownerName, double balance) {
        accountsCount++;
        this.accountNumber = accountsCount;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public static int getAccountsCount() {
        return accountsCount;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("مبلغ الإيداع لازم يكون أكبر من صفر");
        }
        balance += amount;
    }

    public abstract void withdraw(double amount);
}