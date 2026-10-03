import bank.Account;

public class Main {

    public static void main(String[] args) {
        Account a1 = new Account("Ahmed", 5000);
        Account a2 = new Account("Mohamed", 10000);
        Account a3 = new Account("Yossef", 15000);

        System.out.println(a1.getAccountNumber() + " - " + a1.getOwnerName());
        System.out.println(a2.getAccountNumber() + " - " + a2.getOwnerName());
        System.out.println(a3.getAccountNumber() + " - " + a3.getOwnerName());

        System.out.println("Total accounts: " + Account.getAccountsCount());

        a1.deposit(1000);
        System.out.println(a1.getBalance());
        System.out.println(a2.getBalance());
        System.out.println(a3.getBalance());
    }
}