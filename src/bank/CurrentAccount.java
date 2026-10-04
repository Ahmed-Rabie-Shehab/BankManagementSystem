package bank;

public class CurrentAccount extends Account implements Freezable {
    private final double overdraftLimit;
    private boolean frozen;

    public CurrentAccount(String ownerName, double balance, double overdraftLimit) {
        super(ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (frozen) {
            throw new IllegalStateException("الحساب مجمد");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("المبلغ لازم يكون أكبر من صفر");
        }
        if (balance - amount < -overdraftLimit) {
            throw new InsufficientFundsException("تعديت حد السحب على المكشوف: " + overdraftLimit);
        }
        balance -= amount;
    }

    @Override
    public void freeze() {
        frozen = true;
    }

    @Override
    public void unfreeze() {
        frozen = false;
    }

    @Override
    public boolean isFrozen() {
        return frozen;
    }
}
