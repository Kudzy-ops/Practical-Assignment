import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        // Mixed list, referenced only through the Account type
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-001", 1000.00, 500.00));  // min balance 500
        accounts.add(new CurrentAccount("CUR-001", 300.00, 200.00));   // overdraft limit 200
        accounts.add(new SavingsAccount("SAV-002", 800.00, 500.00));
        accounts.add(new CurrentAccount("CUR-002", 1500.00, 500.00));

        System.out.println("=== Opening balances ===");
        for (Account a : accounts) {
            System.out.printf("  [%s] %.2f%n", a.getAccountNumber(), a.getBalance());
        }

        // Round 1: ordinary withdrawal
        System.out.println("\n=== Round 1: withdraw 200 from every account ===");
        for (Account a : accounts) {
            a.withdraw(200.00); // runtime type decides which withdraw() runs
        }

        // Round 2: edge cases
        //  - SAV-002 (600) is rejected: 600 - 250 would fall below its 500 minimum
        //  - CUR-001 (100) goes to -150: overdraft, but within its 200 limit
        System.out.println("\n=== Round 2: withdraw 250 from every account (edge cases) ===");
        for (Account a : accounts) {
            a.withdraw(250.00);
        }

        // Round 3: a withdrawal that exceeds the overdraft limit
        System.out.println("\n=== Round 3: withdraw 100 from every account ===");
        for (Account a : accounts) {
            a.withdraw(100.00);
        }

        // Month-end: each subclass's own endOfMonth() runs
        System.out.println("\n=== Month-end processing ===");
        for (Account a : accounts) {
            a.endOfMonth();
        }

        System.out.println("\n=== Closing balances ===");
        for (Account a : accounts) {
            System.out.printf("  [%s] %.2f%n", a.getAccountNumber(), a.getBalance());
        }
    }
}
