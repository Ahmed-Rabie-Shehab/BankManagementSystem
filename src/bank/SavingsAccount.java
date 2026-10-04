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
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("المبلغ لازم يكون أكبر من صفر");
        }
        if (amount > balance) {
            throw new IllegalStateException("الرصيد غير كافٍ. الرصيد الحالي: " + balance);
        }
        balance -= amount;
    }
}