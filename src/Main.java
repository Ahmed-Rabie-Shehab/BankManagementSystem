import bank.Account;
import bank.Bank;
import bank.CurrentAccount;
import bank.SavingsAccount;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank(5);

        SavingsAccount s1 = new SavingsAccount("Ahmed", 5000, 0.10);
        CurrentAccount c1 = new CurrentAccount("Sara", 1000, 5000);
        SavingsAccount s2 = new SavingsAccount("Yossef", 15000, 0.05);

        bank.addAccount(s1);
        bank.addAccount(c1);
        bank.addAccount(s2);

        try {
            s1.withdraw(9000);
            System.out.println("تم السحب من حساب Ahmed");
        } catch (IllegalArgumentException e) {
            System.out.println("مدخل غلط: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("العملية مرفوضة: " + e.getMessage());
        }

        try {
            c1.withdraw(3000);
            System.out.println("تم السحب من حساب Sara");
        } catch (IllegalArgumentException e) {
            System.out.println("مدخل غلط: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("العملية مرفوضة: " + e.getMessage());
        }


        System.out.println("---");
        bank.printAll();
    }
}