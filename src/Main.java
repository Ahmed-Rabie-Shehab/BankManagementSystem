import bank.Account;

public class Main {

    public static void main(String[] args) {
        Account account = new Account(1001, "Ahmed", 5000);
        System.out.println(account.getBalance());

        boolean ok = account.withdraw(1000);
        System.out.println(ok + " | " + account.getBalance());

        boolean negative = account.withdraw(-500);
        System.out.println(negative + " | " + account.getBalance());

        boolean tooMuch = account.withdraw(99999);
        System.out.println(tooMuch + " | " + account.getBalance());

        boolean zero = account.withdraw(0);
        System.out.println(zero + " | " + account.getBalance());
    }
}