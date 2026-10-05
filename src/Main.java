import bank.Bank;
import bank.CurrentAccount;
import bank.InsufficientFundsException;
import bank.SavingsAccount;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();

        SavingsAccount s1 = new SavingsAccount("Ahmed", 5000, 0.10);
        CurrentAccount c1 = new CurrentAccount("Sara", 1000, 5000);

        bank.addAccount(s1);
        bank.addAccount(c1);
        bank.removeAccount(1);
        bank.printAll();


        System.out.println("---");

        System.out.println("مجمد؟ " + c1.isFrozen());
        c1.freeze();
        System.out.println("مجمد؟ " + c1.isFrozen());

        try {
            c1.withdraw(100);
            System.out.println("تم السحب");
        } catch (IllegalStateException e) {
            System.out.println("مرفوض: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("رصيد غير كافٍ: " + e.getMessage());
        }
        c1.unfreeze();


        try {
            c1.withdraw(100);
            System.out.println("تم السحب بعد الفك");
        } catch (IllegalStateException e) {
            System.out.println("مرفوض: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("رصيد غير كافٍ: " + e.getMessage());
        }

        System.out.println("---");
        bank.printAll();
    }
}