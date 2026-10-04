package bank;

public class SavingsAccount extends Account {

    private final double interestRate;

    public SavingsAccount(String ownerName, double balance, double interestRate) {
        super(ownerName, balance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double current = getBalance();
        double interest = current * interestRate;
        deposit(interest);
    }
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}