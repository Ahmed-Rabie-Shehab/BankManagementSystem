import bank.Bank;
import bank.CurrentAccount;
import bank.InsufficientFundsException;
import bank.SavingsAccount;
import bank.Transaction;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();

        SavingsAccount s1 = new SavingsAccount("Ahmed", 5000, 0.10);
        CurrentAccount c1 = new CurrentAccount("Sara", 1000, 5000);

        bank.addAccount(s1);
        bank.addAccount(c1);

        bank.printAll();
        System.out.println("Total: " + bank.totalBalance());

        System.out.println("--- عمليات حساب Ahmed ---");
        s1.deposit(1000);
        s1.withdraw(2000);
        s1.addInterest();

        for (Transaction t : s1.getTransactions()) {
            System.out.println(t);
        }

        System.out.println("--- عمليات مرفوضة ---");
        try {
            s1.withdraw(999999);
        } catch (InsufficientFundsException e) {
            System.out.println("رصيد غير كافٍ: " + e.getMessage());
        }

        try {
            s1.deposit(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("مدخل غلط: " + e.getMessage());
        }

        System.out.println("عدد العمليات المسجلة: " + s1.getTransactions().size());

        System.out.println("--- الحساب الجاري ---");
        c1.withdraw(3000);
        c1.freeze();
        try {
            c1.withdraw(100);
        } catch (IllegalStateException e) {
            System.out.println("مرفوض: " + e.getMessage());
        }

        for (Transaction t : c1.getTransactions()) {
            System.out.println(t);
        }

        System.out.println("--- الحالة النهائية ---");
        bank.printAll();
    }
}