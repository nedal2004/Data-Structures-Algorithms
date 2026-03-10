package models;
public class SavingAccount extends Account {
    private double interstRate;

    public SavingAccount(String accountNumber, String ownerName, int pin, double initialBalance, double interestRate) {
        super(accountNumber, ownerName, pin, initialBalance);
        this.interstRate = interstRate;
    }


    @Override
    public void withdraw(double amount) throws  InsufficientFundsException{
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive!");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient Funds in savings Account");
        }
        balance -= amount;
        record(amount, "Saving Withdraw : " + amount + "| new Balance is:" + balance);


    }

    public void applyInterest() {
        double interestAmount = balance * interstRate;
        balance += interestAmount;
        record(interestAmount, "Interest Apply : " + interestAmount + "| new Balance is:" + balance);


    }
}
