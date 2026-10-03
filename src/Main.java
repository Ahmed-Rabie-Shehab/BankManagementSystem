import bank.Account;
import bank.Bank;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank(2);

        System.out.println(bank.addAccount(new Account("Ahmed", 5000)));
        System.out.println(bank.addAccount(new Account("Mohamed", 10000)));
        System.out.println(bank.addAccount(new Account("Yossef", 15000)));
    }
}