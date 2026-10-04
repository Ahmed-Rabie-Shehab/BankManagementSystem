package bank;

public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String ownerName, double balance, double overdraftLimit) {
        super(ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("المبلغ لازم يكون أكبر من صفر");
        }
        if (balance - amount < -overdraftLimit) {
            throw new IllegalStateException("تعديت حد السحب على المكشوف: " + overdraftLimit);
        }
        balance -= amount;
    }
}
