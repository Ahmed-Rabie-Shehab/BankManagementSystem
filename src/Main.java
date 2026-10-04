import bank.Account;
import bank.Bank;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank(5);

        System.out.println(bank.totalBalance());

        bank.addAccount(new Account("Ahmed", 5000));
        bank.addAccount(new Account("Mohamed", 10000));
        bank.addAccount(new Account("Yossef", 15000));

        System.out.println(bank.totalBalance());
    }
}