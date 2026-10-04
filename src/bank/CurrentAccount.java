package bank;

public class CurrentAccount extends Account {
    private double overdraftLimit;
    public CurrentAccount(String ownerName, double balance, double overdraftLimit) {
        super(ownerName, balance);
        this.overdraftLimit= overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0)
            return false;
        if (balance - amount < -overdraftLimit)
            return false;
        balance -= amount;
            return true;
    }
}
