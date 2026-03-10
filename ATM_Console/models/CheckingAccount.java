package models;
public class CheckingAccount extends Account {
    private double transactionFee;

    public CheckingAccount(String accountNumber, String ownerName, int pin, double initialBalance, double transactionFee) throws IllegalArgumentException {
        super(accountNumber, ownerName, pin, initialBalance);
        if (transactionFee < 0) {
            throw new IllegalArgumentException("Transaction Fee  must be positive !");
        }
        this.transactionFee = transactionFee;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive!");
        }
        double totalAmount = amount+this.transactionFee;
        if (totalAmount > balance) {
            throw new InsufficientFundsException("Insufficient Funds in savings Account");
        }
        balance -= totalAmount;
        record(totalAmount, "Checking Account Withdraw : " + amount + "| Transaction Fee:" + this.transactionFee + "| New Balance is:" + balance);

    }
}
