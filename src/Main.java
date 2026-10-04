import bank.Account;
import bank.Bank;
import bank.CurrentAccount;
import bank.SavingsAccount;
public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank(5);

        bank.addAccount(new Account("Ahmed", 5000));
        bank.addAccount(new Account("Mohamed", 10000));
        bank.addAccount(new Account("Yossef", 15000));

        System.out.println(bank.totalBalance());

        Account found = bank.findAccount(2);
        if (found != null) {
            System.out.println(found.getOwnerName() + " | " + found.getBalance());
            found.deposit(5000);
            System.out.println(bank.totalBalance());
        } else {
            System.out.println("Account not found");
        }

        Account missing = bank.findAccount(99);
        System.out.println(missing);

        SavingsAccount s = new SavingsAccount("Kareem", 20000, 0.10);
        System.out.println(s.getBalance());
        s.addInterest();
        System.out.println(s.getBalance());

        CurrentAccount c = new CurrentAccount("Sara", 1000, 5000);
        System.out.println(c.withdraw(3000) + " | " + c.getBalance());
        System.out.println(c.withdraw(9000) + " | " + c.getBalance());

        Account first = new Account("Ali", 1000);
        Account second = new CurrentAccount("Sara", 1000, 5000);

        Bank b2 = new Bank(3);
        b2.addAccount(first);
        b2.addAccount(second);

        System.out.println(first.withdraw(3000));
        System.out.println(second.withdraw(3000));
    }
}