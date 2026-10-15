/**
 * Abstract base class: shared state and behaviour for all account types.
 * Subclasses decide how withdrawals and month-end processing work.
 */
public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Concrete: identical for every account type
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("  [" + accountNumber + "] Deposit rejected: amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("  [%s] Deposited %.2f. New balance: %.2f%n", accountNumber, amount, balance);
    }

    // Concrete: identical for every account type
    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Abstract: each account type has its own withdrawal rules
    public abstract void withdraw(double amount);

    // Abstract hook: each account type has its own month-end processing
    public abstract void endOfMonth();
}
