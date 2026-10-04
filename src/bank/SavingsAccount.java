package bank;

public class SavingsAccount extends Account {

    private double interestRate;
    public SavingsAccount(String ownerName, double balance, double interestRate) {
        super(ownerName, balance);
        this.interestRate = interestRate;
    }
    public void addInterest() {
        double current = getBalance();
        double interest = current * interestRate;
        deposit(interest);
    }
}