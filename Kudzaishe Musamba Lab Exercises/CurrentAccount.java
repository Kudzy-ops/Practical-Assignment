/**
 * Current account: may go negative up to an overdraft limit;
 * charged a fixed maintenance fee at month end (no interest).
 */
public class CurrentAccount extends Account {
    private static final double MONTHLY_FEE = 5.00; // fixed
    private final double overdraftLimit;

    public CurrentAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [" + accountNumber + "] Withdrawal rejected: amount must be positive.");
        } else if (balance - amount < -overdraftLimit) {
            System.out.printf("  [%s] Withdrawal of %.2f REJECTED: would exceed overdraft limit of %.2f (current: %.2f).%n",
                    accountNumber, amount, overdraftLimit, balance);
        } else {
            balance -= amount;
            String note = balance < 0 ? " (in overdraft)" : "";
            System.out.printf("  [%s] Withdrew %.2f. New balance: %.2f%s%n", accountNumber, amount, balance, note);
        }
    }

    @Override
    public void endOfMonth() {
        balance -= MONTHLY_FEE;
        System.out.printf("  [%s] Current month-end: maintenance fee of %.2f deducted. Balance: %.2f%n",
                accountNumber, MONTHLY_FEE, balance);
    }
}
